package com.example.fqw.exceptionHandlers;


import com.example.fqw.controller.ClientController;
import com.example.fqw.exception.ClientException.ClientNotFoundException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.service.ClientService;
import com.example.fqw.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice(basePackageClasses = {ClientController.class})
@Slf4j
public class ClientExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleClientNotFoundException(ClientNotFoundException exception) {
        LogUtils.getErrorLogForExceptionHandler(exception);
        return new ResponseError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
