package com.example.FQW.response.exception.PlaceException;

public class PlaceAlreadyExistsException extends RuntimeException {

    public PlaceAlreadyExistsException() {
        super("Место уже существует");
    }
}
