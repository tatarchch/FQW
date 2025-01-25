package com.example.FQW.exception.exceptionHandlers;

import com.example.FQW.exception.CalendarException.CalendarNotFoundDateException;
import com.example.FQW.exception.CalendarException.CalendarNotFoundIdException;
import com.example.FQW.exception.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CalendarExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleCalendarNotFoundDateException(CalendarNotFoundDateException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleCalendarNotFoundIdException(CalendarNotFoundIdException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
