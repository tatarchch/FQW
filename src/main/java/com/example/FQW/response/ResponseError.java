package com.example.FQW.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseError {

    private final String message;

    private final HttpStatus httpStatus;

}
