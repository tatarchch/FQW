package com.example.fqw.exception.handlers;

import com.example.fqw.exception.ResponseError;
import com.example.fqw.exception.PetServiceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleServiceNorFoundException(PetServiceNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

}
