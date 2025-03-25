package com.example.fqw.exception;

public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException() {
        super("Администратор с такими данными не найден");
    }

}
