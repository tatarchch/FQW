package com.example.fqw.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ClassNameForSaveExceptionHandler {

    CLIENT_CONTROLLER("ClientControllerImpl"),
    PLACE_CONTROLLER("PlaceControllerImpl"),
    CALENDAR_CONTROLLER("CalendarControllerImpl"),
    PET_SERVICE_CONTROLLER("PetServiceControllerImpl"),
    MASTER_CONTROLLER("MasterControllerImpl"),
    RECORD_CONTROLLER("RecordControllerImpl");

    public static ClassNameForSaveExceptionHandler getByValue(String value) {
        return Arrays.stream(ClassNameForSaveExceptionHandler.values())
                .filter(nameEnum -> nameEnum.getClassName().equals(value))
                .findFirst()
                .orElse(null);
    }

    private final String className;

}
