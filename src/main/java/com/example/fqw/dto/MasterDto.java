package com.example.fqw.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MasterDto {

    @Null(message = "Id должен быть null при создании")
    private Long id;

    @NotBlank(message = "Имя мастера не может быть пустым")
    @Pattern(regexp = "^(\\p{Lu}\\p{L}*)(\\s\\p{Lu}\\p{L}*)*$",
            message = "Каждое слово в имени мастера должно начинаться с заглавной буквы")
    @Size(min = 2, max = 225, message = "ФИО должно быть от 2 до 225 символов")
    private String name;

    @NotNull(message = "Уровень мастера обязательно должен быть указан")
    @Min(value = 1, message = "Уровень мастера не должен быть ниже 1")
    @Max(value = 3, message = "Уровень мастера не должен быть выше 3")
    private Integer level;

}
