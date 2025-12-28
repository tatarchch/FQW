package com.example.fqw.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CalendarDto {

    @Null(message = "Id должен быть null при создании")
    private Long id;

    @NotNull(message = "Дата не может быть null")
    @FutureOrPresent(message = "Дата должна быть не раньше, чем сегодня")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

}
