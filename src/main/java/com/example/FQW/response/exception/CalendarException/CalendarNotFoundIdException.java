package com.example.FQW.response.exception.CalendarException;

public class CalendarNotFoundIdException extends RuntimeException {

    public CalendarNotFoundIdException() {
        super("Даты с данным айди не существует");
    }

}
