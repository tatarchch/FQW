package com.example.fqw.exception.PetServiceException;

public class PetServiceNotFoundException extends RuntimeException {
    public PetServiceNotFoundException() {
        super("Услуга не найдена");
    }
}
