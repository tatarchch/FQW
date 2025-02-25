package com.example.fqw.exception.MasterException;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException() {
        super("Мастер не найден");
    }

}
