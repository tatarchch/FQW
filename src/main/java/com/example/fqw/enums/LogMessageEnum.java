package com.example.fqw.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogMessageEnum {

    LOGIN("Клиент с логином '%s' начал использовать бота"),

    PICK_PLACE("Пользователь с логином '%s' выбрал место '%s'"),

    PICK_MASTER("Пользователь с логином '%s' выбрал мастера '%s'"),

    PICK_SERVICE("Пользователь с логином '%s' выбрал услугу '%s'"),

    PICK_DATE("Пользователь с логином '%s' выбрал день '%s'"),

    PICK_TIME("Пользователь с логином '%s' выбрал время '%s'"),

    REPLY_KEYBOARD("%s (основная клавитура)"),

    INLINE_KEYBOARD("%s (встроенная клавитура)"),

    CONFIRM("Запись пользователем '%s' создана." +
            " (Id услуги: '%s'." +
            " Id мастера: '%s'." +
            " Дата проведения: '%s'." +
            " Время проведения: '%s'.)"),

    CANCEL("Пользователь %s отменил запись");

    private final String text;
}
