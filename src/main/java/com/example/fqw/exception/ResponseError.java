package com.example.fqw.exception;

import org.springframework.http.HttpStatus;

public record ResponseError(String message, HttpStatus httpStatus) {

}
