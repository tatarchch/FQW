package com.example.fqw.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlaceDto {

    private Long id;

    @NotBlank(message = "Название места не может быть пустым")
    @Size(min = 2, max = 40, message = "Название места должно быть от 2 до 40 символов")
    private String name;

    @NotBlank(message = "Адрес места не может быть пустым")
    @Size(min = 2, max = 80, message = "Адрес места должно быть от 2 до 80 символов")
    private String address;

}
