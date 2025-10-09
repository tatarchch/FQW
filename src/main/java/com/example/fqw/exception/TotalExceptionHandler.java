package com.example.fqw.exception;

import com.example.fqw.enums.ClassNameForSaveExceptionHandler;
import com.example.fqw.enums.SaveErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
@Slf4j
public class TotalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleUsernameNotFoundDateException(UsernameNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleCalendarNotFoundDateException(CalendarNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleClientNotFoundException(ClientNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleClientAlreadyExsistsException(ClientAlreadyExistsException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleMasterAlreadyExistsException(MasterAlreadyExistsException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleMasterNotFoundException(MasterNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handlePlaceNotfoundException(PlaceNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleRecordException(RecordException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleClientOtherException(OtherException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseError(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseError handleSaveException(DataIntegrityViolationException exception, HandlerMethod handlerMethod) {
        ClassNameForSaveExceptionHandler className = ClassNameForSaveExceptionHandler.getByValue(handlerMethod.getBeanType().getSimpleName());

        log.error(exception.getMessage(), exception);

        return switch (className) {
            case CLIENT_CONTROLLER -> new ResponseError(SaveErrorEnum.CLIENT.getMessage());
            case PLACE_CONTROLLER -> new ResponseError(SaveErrorEnum.PLACE.getMessage());
            case CALENDAR_CONTROLLER -> new ResponseError(SaveErrorEnum.CALENDAR.getMessage()); //?
            case PET_SERVICE_CONTROLLER -> new ResponseError(SaveErrorEnum.PET_SERVICE.getMessage());
            case MASTER_CONTROLLER -> new ResponseError(SaveErrorEnum.MASTER.getMessage());
            default -> new ResponseError(new OtherException().getMessage());
        };
    }

}
