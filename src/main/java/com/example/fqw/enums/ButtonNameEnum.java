package com.example.fqw.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ButtonNameEnum {

    START("/start"),
    LOGIN("логин"),
    APPLY("подтвердить"),
    CANCEL("отменить"),
    GET_PLACE("выбрать место"),
    GET_MASTER("выбрать мастера"),
    GET_SERVICE("выбрать услугу"),
    GET_MASTER_BY_SERVICE("выбрать мастера с учётом услуги"),
    GET_SERVICE_BY_MASTER("выбрать услугу с учётом мастера"),
    GET_DATE("выбрать дату"),
    GET_TIME("выбрать время"),
    EXCEPTION("ошибка");


    public static ButtonNameEnum getByValue(String value) {
        return Arrays.stream(ButtonNameEnum.values())
                .filter(nameEnum -> nameEnum.getButtonName().equals(value))
                .findFirst()
                .orElse(EXCEPTION);
    }

    private final String buttonName;

}
