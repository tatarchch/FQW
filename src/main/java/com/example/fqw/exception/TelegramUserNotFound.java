package com.example.fqw.exception;

public class TelegramUserNotFound extends RuntimeException {

    public TelegramUserNotFound() {
        super("Пользователь с данным id не найден");
    }

}
