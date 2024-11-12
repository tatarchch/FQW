package com.example.FQW.service;

import com.example.FQW.dto.ClientDto;
import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.entity.Client;
import com.example.FQW.mapper.ClientMapper;
import com.example.FQW.repositories.ClientRepository;
import com.example.FQW.response.exception.ClientException.ClientAlreadyExistsException;
import com.example.FQW.response.exception.ClientException.ClientNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final PreOrderService preOrderService;

    public List<ClientDto> getAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    public ClientDto getClientByLoginAndPassword(String login, String password) {
        return clientRepository.findClientByLoginAndPassword(login, password)
                .map(clientMapper::toDTO)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientDto getClientByLogin(String login) {
        return clientRepository.findClientByLogin(login)
                .map(clientMapper::toDTO)
                .orElseThrow(ClientNotFoundException::new);
    }

    public ClientDto saveClient(ClientDto clientDto) {
        return Optional.of(clientDto)
                .map(clientMapper::toEntity)
                .map(clientRepository::save)
                .map(clientMapper::toDTO)
                .orElseThrow(ClientAlreadyExistsException::new);
    }

    public PreOrderDto login(String login, String password) {
        return Optional.of(this.getClientByLoginAndPassword(login, password))
                .map(ClientDto::getId)
                .map(preOrderService::callClient)
                .orElseThrow(ClientNotFoundException::new);
    }

    public PreOrderDto registration(ClientDto clientDto) {
        return Optional.of(clientDto)
                .map(clientMapper::toEntity)
                .map(Client::getLogin)
                .map(clientRepository::existsClientByLogin)
                .filter(Boolean.FALSE::equals)
                .map(predicate -> clientDto)
                .map(this::saveClient)
                .map(ClientDto::getId)
                .map(preOrderService::callClient)
                .orElseThrow(ClientAlreadyExistsException::new);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(ClientNotFoundException::new);
    }
}
