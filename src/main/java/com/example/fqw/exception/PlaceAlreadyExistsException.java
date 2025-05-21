package com.example.fqw.exception;

import com.example.fqw.dto.PlaceDto;

public class PlaceAlreadyExistsException extends RuntimeException {

    public PlaceAlreadyExistsException(PlaceDto placeDto) {
        super(String.format("Место по адресу '%s' уже существует", placeDto.getAddress()));
    }

}
