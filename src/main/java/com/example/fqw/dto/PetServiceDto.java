package com.example.fqw.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PetServiceDto {

    @Null(message = "Id должен быть null при создании")
    private Long id;

    @NotBlank(message = "Название услуги не может быть пустым")
    @Size(min = 2, max = 40, message = "Название услуги должно быть от 2 до 40 символов")
    private String name;

    @NotNull(message = "Цена услуги не может быть null")
    @Positive(message = "Стоимость услуги должна быть положительным числом")
    private Integer cost;

}
