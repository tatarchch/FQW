package com.example.FQW.exception.ClientException;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Неверный логин или пароль");
    }

}
