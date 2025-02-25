package com.example.fqw.exception.MasterException;

public class MasterAlreadyExistsException extends RuntimeException{

    public MasterAlreadyExistsException() {
        super("Мастер уже существует");
    }

}
