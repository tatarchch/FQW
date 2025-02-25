package com.example.fqw.exceptionHandlers;

import com.example.fqw.exception.ResponseError;
import com.example.fqw.utils.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
@Slf4j
public class SaveExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleSaveException(DataIntegrityViolationException exception, HandlerMethod handlerMethod) {
        LogUtils.getErrorLogForExceptionHandler(exception);

        return switch (handlerMethod.getBeanType().getSimpleName()){
            case "ClientController" -> new ResponseError("клиент", HttpStatus.CONFLICT) ;
            case "PlaceController" -> new ResponseError("место", HttpStatus.CONFLICT);
            default -> new ResponseError("", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

}
