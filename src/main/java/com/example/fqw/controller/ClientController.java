package com.example.fqw.controller;


import com.example.fqw.dto.ClientDto;
import com.example.fqw.email.EmailSender;
import com.example.fqw.exception.ClientException.ClientAlreadyExistsException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.service.ClientService;
import com.example.fqw.utils.LogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final EmailSender senderService;

    @GetMapping("/login")
    public ClientDto login(@RequestParam("login") String login, @RequestParam("password") String password) {
        return clientService.getClientByLoginAndPassword(login, password);
    }

    @PostMapping("/registration")
    public ClientDto registration(ClientDto clientDto) {
        return clientService.registration(clientDto);
    }

}
