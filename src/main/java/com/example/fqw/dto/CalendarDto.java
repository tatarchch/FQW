package com.example.fqw.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

//@Builder
@Data
//@RequiredArgsConstructor
@Schema(description = "календарь")
public class CalendarDto {

    private Long id;

    private LocalDate date;

}
