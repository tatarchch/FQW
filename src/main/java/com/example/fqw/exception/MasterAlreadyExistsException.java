package com.example.fqw.exception;

import com.example.fqw.dto.MasterDto;

public class MasterAlreadyExistsException extends RuntimeException {

    public MasterAlreadyExistsException(MasterDto masterDto) {
        super(String.format("Мастер с именем %s и уровнем %s уже существует",
                masterDto.getName(),
                masterDto.getLevel())
        );
    }

}
