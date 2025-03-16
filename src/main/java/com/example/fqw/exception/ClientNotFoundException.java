package com.example.fqw.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Пользовательские данные неверны");
    }

}
