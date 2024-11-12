package com.example.FQW.response.exception.ClientException;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Неверный логин или пароль");
    }

}
