package com.example.FQW.response.exception.CalendarException;

public class CalendarNotFoundDateException extends RuntimeException {

    public CalendarNotFoundDateException() {
        super("Данной даты не существует");
    }

}
