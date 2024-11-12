package com.example.FQW.response.exception.RecordException;

public class RecordException extends RuntimeException{
    public RecordException() {
        super("Запись не найдена");
    }
}
