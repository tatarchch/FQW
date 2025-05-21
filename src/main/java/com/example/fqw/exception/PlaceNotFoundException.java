package com.example.fqw.exception;

public class PlaceNotFoundException extends RuntimeException {

    public PlaceNotFoundException(Long id) {
        super(String.format("Место с id '%s' не найдено", id));
    }

    public PlaceNotFoundException(String name) {
        super(String.format("Место с названем '%s' не найдено", name));
    }

}
