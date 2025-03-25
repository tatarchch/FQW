package com.example.fqw.exception.handlers;

import com.example.fqw.exception.OtherException;
import com.example.fqw.exception.ResponseError;
import com.example.fqw.enums.ClassNameForSaveExceptionHandler;
import com.example.fqw.enums.SaveErrorEnum;
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
        ClassNameForSaveExceptionHandler className = ClassNameForSaveExceptionHandler.getByValue(handlerMethod.getBeanType().getSimpleName());

        LogUtils.getErrorLogForExceptionHandler(exception);

        return switch (className){
            case CLIENT_CONTROLLER -> new ResponseError(SaveErrorEnum.CLIENT.getMessage());
            case PLACE_CONTROLLER -> new ResponseError(SaveErrorEnum.PLACE.getMessage());
            case CALENDAR_CONTROLLER -> new ResponseError(SaveErrorEnum.CALENDAR.getMessage()); //?
            case PETSERVICE_CONTROLLER -> new ResponseError(SaveErrorEnum.PET_SERVICE.getMessage());
            case MASTER_CONTROLLER -> new ResponseError(SaveErrorEnum.MASTER.getMessage());
            default -> new ResponseError(new OtherException().getMessage());
        };
    }

}
