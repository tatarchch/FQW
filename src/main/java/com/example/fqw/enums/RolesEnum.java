package com.example.fqw.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RolesEnum {

    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

}
