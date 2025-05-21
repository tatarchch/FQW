package com.example.fqw.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Клиент с таким именем не найден");
    }

    public ClientNotFoundException(Long id) {
        super(String.format("Клиент с id '%s' не найден", id));
    }

    public ClientNotFoundException(String login) {
        super(String.format("Клиент с логином '%s' не найден", login));
    }

    public ClientNotFoundException(String login, String password) {
        super(String.format("Клиент с логином '%s' и паролем '%s' не найден", login, password));
    }

}
