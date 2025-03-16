package com.example.fqw.exception;

public class CalendarNotFoundDateException extends RuntimeException {

    public CalendarNotFoundDateException() {
        super("Данной даты не существует");
    }

}
