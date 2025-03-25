package com.example.fqw.exception;

public class LowAccessLevelException extends RuntimeException{

    public LowAccessLevelException() {
        super("Недостаточно прав для доступа");
    }
}
