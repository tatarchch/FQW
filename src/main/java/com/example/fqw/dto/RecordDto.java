package com.example.fqw.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class RecordDto {

    @Null(message = "Id должен быть null при создании")
    private Long id;

    @NotBlank(message = "Промежуток времени не может быть пустым")
    @Size(min = 2, max = 20, message = "Промежуток времени должен быть от 2 до 20 символов")
    private String timing;

    @NotNull(message = "Дата не может быть null")
    @FutureOrPresent(message = "Дата должна быть не раньше, чем сегодня")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @NotBlank(message = "Имя клиента не может быть пустым")
    @Size(min = 2, max = 20, message = "Статус должен    быть от 2 до 20 символов")
    private String status;

    @NotNull(message = "Клиент не может быть null")
    @Valid
    private ClientDto clientDto;

    @NotNull(message = "Мастер не может быть null")
    @Valid
    private MasterDto masterDto;

    @NotNull(message = "Место не может быть null")
    @Valid
    private PlaceDto placeDto;

    @NotNull(message = "Услуга не может быть null")
    @Valid
    private PetServiceDto petServiceDto;

}
