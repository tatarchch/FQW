package com.example.fqw.exception;

public class PlaceAlreadyExistsException extends RuntimeException {

    public PlaceAlreadyExistsException() {
        super("Место уже существует");
    }

}
