package com.example.fqw.exceptionHandlers;

import com.example.fqw.controller.PlaceController;
import com.example.fqw.exception.PlaceException.PlaceNotFoundException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.service.PlaceService;
import com.example.fqw.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PlaceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handlePlaceNotfoundException(PlaceNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
