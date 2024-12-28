package com.example.FQW.exception.ClientException;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException() {
        super("Пользователь с таким логином уже существует");
    }
}
