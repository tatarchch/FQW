package com.example.fqw.exception;

import java.time.LocalDate;

public class CalendarNotFoundException extends RuntimeException {

    public CalendarNotFoundException(Long id) {
        super(String.format("Дата с id '%s' не найдена", id));
    }

    public CalendarNotFoundException(LocalDate date) {
        super(String.format("Дата '%s' не найдена", date.toString()));
    }

}
