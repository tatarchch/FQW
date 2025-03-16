package com.example.fqw.exception;

public class PetServiceNotFoundException extends RuntimeException {
    public PetServiceNotFoundException() {
        super("Услуга не найдена");
    }
}
