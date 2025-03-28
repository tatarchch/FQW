package com.example.fqw.exception;

public class RecordException extends RuntimeException{

    public RecordException() {
        super("Запись не найдена");
    }

}
