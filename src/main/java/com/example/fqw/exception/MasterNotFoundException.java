package com.example.fqw.exception;

public class MasterNotFoundException extends RuntimeException {

    public MasterNotFoundException() {
        super("Мастер не найден");
    }

}
