package com.example.FQW.exception.MasterException;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException() {
        super("Мастер не найден");
    }

}
