package com.example.FQW.telegram.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
    GET_TIME("выбрать время");

    private final String buttonName;



}
