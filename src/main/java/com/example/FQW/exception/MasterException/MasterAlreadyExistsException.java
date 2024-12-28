package com.example.FQW.exception.MasterException;

public class MasterAlreadyExistsException extends RuntimeException{

    public MasterAlreadyExistsException() {
        super("Мастер уже существует");
    }

}
