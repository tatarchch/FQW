package com.example.fqw.exception.PlaceException;

public class PlaceAlreadyExistsException extends RuntimeException {

    public PlaceAlreadyExistsException() {
        super("Место уже существует");
    }
}
