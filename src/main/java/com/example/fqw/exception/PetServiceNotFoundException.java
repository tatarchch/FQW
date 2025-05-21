package com.example.fqw.exception;

public class PetServiceNotFoundException extends RuntimeException {

    public PetServiceNotFoundException(Long id) {
        super(String.format("Услуга с id '%s' не найдена", id));
    }

    public PetServiceNotFoundException(String name) {
        super(String.format("Услуга с названием '%s' не найдена", name));
    }

}
