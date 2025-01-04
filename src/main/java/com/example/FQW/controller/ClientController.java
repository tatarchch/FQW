package com.example.FQW.controller;


import com.example.FQW.dto.ClientDto;
import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/login")
    public ClientDto login(@RequestParam("login") String login, @RequestParam("password") String password) {
        return clientService.getClientByLoginAndPassword(login, password);
    }

    @PostMapping("/registration")
    public ClientDto registration(ClientDto clientDto) {
        return clientService.registration(clientDto);
    }

}
