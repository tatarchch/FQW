package com.example.fqw.exception.ClientException;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException() {
        super("Пользователь с таким логином уже существует");
    }
}
