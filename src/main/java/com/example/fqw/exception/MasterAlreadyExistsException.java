package com.example.fqw.exception;

public class MasterAlreadyExistsException extends RuntimeException{

    public MasterAlreadyExistsException() {
        super("Мастер уже существует");
    }

}
