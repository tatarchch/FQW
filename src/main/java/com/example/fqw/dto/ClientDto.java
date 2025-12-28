package com.example.fqw.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClientDto {

    @Null(message = "Id должен быть null при создании")
    private Long id;

    @NotBlank(message = "Имя клиента не может быть пустым")
    @Pattern(regexp = "^(\\p{Lu}\\p{L}*)(\\s\\p{Lu}\\p{L}*)*$",
            message = "Каждое слово в имени мастера должно начинаться с заглавной буквы")
    @Size(min = 2, max = 225, message = "ФИО должно быть от 2 до 225 символов")
    private String name;

    @NotBlank(message = "Логин клиента не может быть пустым")
    @Size(min = 2, max = 20, message = "Логин должно быть от 2 до 20 символов")
    private String login;

    @NotBlank(message = "Пароль клиента не может быть пустым")
    @Size(min = 2, max = 30, message = "Пароль должно быть от 2 до 30 символов")
    private String password;

    @NotBlank(message = "Email клиента не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Size(min = 2, max = 50, message = "Email должно быть от 2 до 50 символов")
    private String email;

    @Null(message = "Роль должна быть null при создании")
    private String role;

    private String chatId;

}
