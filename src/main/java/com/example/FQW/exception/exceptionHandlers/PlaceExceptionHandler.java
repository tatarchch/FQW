package com.example.FQW.exception.exceptionHandlers;

import com.example.FQW.exception.PlaceException.PlaceAlreadyExistsException;
import com.example.FQW.exception.PlaceException.PlaceNotFoundException;
import com.example.FQW.exception.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PlaceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handlePlaceAlreadyExistsException(PlaceAlreadyExistsException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handlePlaceAlreadyExistsException(PlaceNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
