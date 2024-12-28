package com.example.FQW.exception.RecordException;

public class RecordException extends RuntimeException{
    public RecordException() {
        super("Запись не найдена");
    }
}
