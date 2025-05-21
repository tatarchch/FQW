package com.example.fqw.exception;

public class RecordException extends RuntimeException {

    public RecordException(Long id) {
        super(String.format("Запись с id '%s' не найдена", id));
    }

}
