package com.example.fqw.exception.handlers;

import com.example.fqw.exception.AdminNotFoundException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.utils.LogUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdminExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleAdminNotFoundException(AdminNotFoundException exception) {
        LogUtils.getErrorLogForExceptionHandler(exception);
        return new ResponseError(exception.getMessage());
    }

}
