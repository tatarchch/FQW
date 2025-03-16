package com.example.fqw.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException() {
        super("Пользователь с таким логином уже существует");
    }
}
