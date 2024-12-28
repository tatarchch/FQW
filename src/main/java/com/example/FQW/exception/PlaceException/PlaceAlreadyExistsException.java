package com.example.FQW.exception.PlaceException;

public class PlaceAlreadyExistsException extends RuntimeException {

    public PlaceAlreadyExistsException() {
        super("Место уже существует");
    }
}
