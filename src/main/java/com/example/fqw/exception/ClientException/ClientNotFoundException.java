package com.example.fqw.exception.ClientException;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Пользовательские данные неверны");
    }

}
