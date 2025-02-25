package com.example.fqw.exception.RecordException;

public class RecordException extends RuntimeException{
    public RecordException() {
        super("Запись не найдена");
    }
}
