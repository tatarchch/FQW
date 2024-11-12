package com.example.FQW.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@Schema(description = "календарь")
public class CalendarDto {

    private Long id;

    private LocalDate date;

}
