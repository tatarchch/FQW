package com.example.fqw.controller;


import com.example.fqw.api.ClientControllerInterface;
import com.example.fqw.dto.ClientDto;
import com.example.fqw.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientControllerInterface {

    private final ClientService clientService;

    @Override
    public ClientDto login(String login, String password) {
        return clientService.getClientByLoginAndPassword(login, password);
    }

    @Override
    public ClientDto registration(ClientDto clientDto) {
        return clientService.registration(clientDto);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @Override
    public ClientDto getClientById(Long id) {
        return clientService.getClientById(id);
    }

    @Override
    public ClientDto getClientByLogin(String login) {
        return clientService.getClientByLogin(login);
    }

    @Override
    public ClientDto getClientByLoginAndPassword(String login, String password) {
        return clientService.getClientByLoginAndPassword(login, password);
    }

    @Override
    public ClientDto getClientByName(String name) {
        return clientService.getClientByName(name);
    }

}
