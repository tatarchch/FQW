package com.example.fqw.exception;

public class CalendarNotFoundIdException extends RuntimeException {

    public CalendarNotFoundIdException() {
        super("Даты с данным айди не существует");
    }

}
