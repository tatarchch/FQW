package com.example.fqw.email;

import lombok.Getter;

//@RequiredArgsConstructor
//@Getter
public record EmailMessage(String addressee, String text) {

    public static final String SENDER = "tatarnirov15277@mail.ru";
    public static final String HEADER = "Оповещение о записи от салона по уходу за домашними животными";

}
