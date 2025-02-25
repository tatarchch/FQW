package com.example.fqw.service;

import com.example.fqw.dto.ClientDto;
import com.example.fqw.entity.Client;
import com.example.fqw.exception.ClientException.ClientAlreadyExistsException;
import com.example.fqw.exception.ClientException.ClientNotFoundException;
import com.example.fqw.mapper.ClientMapper;
import com.example.fqw.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    public ClientDto getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDTO)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientDto getClientByLogin(String login) {
        return clientRepository.findClientByLogin(login)
                .map(clientMapper::toDTO)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientDto getClientByLoginAndPassword(String login, String password) {
        return clientRepository.findClientByLoginAndPassword(login, password)
                .map(clientMapper::toDTO)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientDto registration(ClientDto clientDto) {
        return Optional.of(clientDto)
                .map(ClientDto::getLogin)
                .map(clientRepository::existsClientByLogin)
                .filter(Boolean.FALSE::equals)
                .map(predicate -> clientDto)
                .map(clientMapper::toEntity)
                .map(clientRepository::save)
                .map(clientMapper::toDTO)
                .orElseThrow();
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
