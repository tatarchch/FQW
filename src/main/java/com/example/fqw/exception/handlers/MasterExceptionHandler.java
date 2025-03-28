package com.example.fqw.exception.handlers;


import com.example.fqw.exception.MasterAlreadyExistsException;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.utils.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MasterExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleMasterAlreadyExistsException(MasterAlreadyExistsException exception) {
        LogUtils.getErrorLogForExceptionHandler(exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleMasterNotFoundException(MasterNotFoundException exception) {
        LogUtils.getErrorLogForExceptionHandler(exception);
        return new ResponseError(exception.getMessage());
    }

}
