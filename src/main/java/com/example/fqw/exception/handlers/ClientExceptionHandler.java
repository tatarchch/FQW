package com.example.fqw.exception.handlers;


import com.example.fqw.exception.ClientAlreadyExistsException;
import com.example.fqw.exception.ClientNotFoundException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.utils.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleClientNotFoundException(ClientNotFoundException exception) {
        LogUtils.getErrorLogForExceptionHandler(exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleClientAlreadyExsistsException(ClientAlreadyExistsException exception) {
        LogUtils.getErrorLogForExceptionHandler(exception);
        return new ResponseError(exception.getMessage());
    }

}
