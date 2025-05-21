package com.example.fqw.exception;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException() {
        super("Мастер с таким placeId не найден");
    }

    public MasterNotFoundException(Long id) {
        super(String.format("Мастер с id '%s' не найден", id));
    }

    public MasterNotFoundException(String name) {
        super(String.format("Мастер с именеи '%s' не найден", name));
    }

}
