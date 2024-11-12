package com.example.FQW.response.exception.MasterException;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException() {
        super("Мастер не найден");
    }

}
