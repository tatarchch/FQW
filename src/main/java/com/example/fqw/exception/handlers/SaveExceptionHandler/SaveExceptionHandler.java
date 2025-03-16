package com.example.fqw.exception.handlers.SaveExceptionHandler;

import com.example.fqw.exception.ResponseError;
import com.example.fqw.exception.handlers.SaveExceptionHandler.EnumForSave.ClassNameForSaveExceptionHandler;
import com.example.fqw.exception.handlers.SaveExceptionHandler.EnumForSave.MessageForSaveExceptionHandler;
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

    private static final HttpStatus HTTP = HttpStatus.CONFLICT;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleSaveException(DataIntegrityViolationException exception, HandlerMethod handlerMethod) {
        ClassNameForSaveExceptionHandler className = ClassNameForSaveExceptionHandler.getByValue(handlerMethod.getBeanType().getSimpleName());

        LogUtils.getErrorLogForExceptionHandler(exception);

        return switch (className){
            case CLIENT_CONTROLLER -> new ResponseError(MessageForSaveExceptionHandler.CLIENT.getMessage(), HTTP);
            case PLACE_CONTROLLER -> new ResponseError(MessageForSaveExceptionHandler.PLACE.getMessage(), HTTP);
            case CALENDAR_CONTROLLER -> new ResponseError(MessageForSaveExceptionHandler.CALENDAR.getMessage(), HTTP); //?
            case PETSERVICE_CONTROLLER -> new ResponseError(MessageForSaveExceptionHandler.PET_SERVICE.getMessage(), HTTP);
            case MASTER_CONTROLLER -> new ResponseError(MessageForSaveExceptionHandler.MASTER.getMessage(), HTTP);
            case RECORD_CONTROLLER -> new ResponseError("запись", HTTP);
            //default -> new ResponseError("непредвиденная ошибка", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

}
