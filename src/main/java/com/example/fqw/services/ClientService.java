package com.example.fqw.services;

import com.example.fqw.dto.ClientDto;
import com.example.fqw.entity.Client;
import com.example.fqw.enums.RolesEnum;
import com.example.fqw.exception.ClientAlreadyExistsException;
import com.example.fqw.exception.ClientNotFoundException;
import com.example.fqw.mapper.ClientMapper;
import com.example.fqw.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder encoder;

    public List<ClientDto> getAllUsers() {
        return clientRepository.findAllByRole(RolesEnum.USER.getRole()).stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    public ClientDto getClientById(Long id) {
        return clientRepository.findByIdAndRole(id, RolesEnum.USER.getRole())
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public ClientDto getClientByLogin(String login) {
        return clientRepository.findClientByLoginAndRole(login, RolesEnum.USER.getRole())
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ClientNotFoundException(login));
    }

    public ClientDto getClientByName(String name) {
        return clientRepository.findClientByNameAndRole(name, RolesEnum.USER.getRole())
                .map(clientMapper::toDTO)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientDto getClientByLoginAndPassword(String login, String password) {
        return clientRepository.findClientByLoginAndRole(login, RolesEnum.USER.getRole())
                .filter(client -> encoder.matches(password, client.getPassword()))
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ClientNotFoundException(login, password));
    }

    public ClientDto registration(ClientDto clientDto) {
        return Optional.of(clientDto)
                .map(ClientDto::getLogin)
                .map(clientRepository::existsClientByLogin)
                .filter(Boolean.FALSE::equals)
                .map(dto -> {
                    clientDto.setRole(RolesEnum.USER.getRole());
                    return clientDto;
                })
                .map(clientMapper::toEntity)
                .map(client -> {
                    client.setPassword(encoder.encode(client.getPassword()));
                    return client;
                })
                .map(clientRepository::save)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new ClientAlreadyExistsException(clientDto));
    }

    public ClientDto botLogin(String chatId, String userName) {
        return clientRepository.findClientByChatId(chatId)
                .map(clientMapper::toDTO)
                .orElseGet(() -> {
                    Client client = new Client();
                    client.setName(userName);
                    client.setChatId(chatId);
                    return clientMapper.toDTO(clientRepository.save(client));
                });
    }

}
