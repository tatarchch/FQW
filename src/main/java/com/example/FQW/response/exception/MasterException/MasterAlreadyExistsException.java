package com.example.FQW.response.exception.MasterException;

public class MasterAlreadyExistsException extends RuntimeException{

    public MasterAlreadyExistsException() {
        super("Мастер уже существует");
    }

}
