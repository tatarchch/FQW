package com.example.FQW.response.exception.PlaceException;

public class PlaceNotFoundException extends RuntimeException{

    public PlaceNotFoundException() {
        super("Место с данным айди не найдено");
    }
}
