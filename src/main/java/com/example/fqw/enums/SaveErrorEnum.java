package com.example.fqw.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SaveErrorEnum {

    CLIENT("Клиент с такой почтой уже существует"),

    CALENDAR("Календарь с такой датой уже существует"),

    PLACE("Место с таким адрессом уже существует"),

    MASTER("Вы не указали имя или уровень мастера"),

    PET_SERVICE("Услуга с таким названием уже существует или вы не указали цену");

    private final String message;
}
