package com.example.fqw.exception;

import com.example.fqw.dto.ClientDto;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(ClientDto clientDto) {
        super(String.format("Пользователь с логином '%s' уже существует", clientDto.getLogin()));
    }

}
