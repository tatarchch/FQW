package com.example.FQW.dto;

import lombok.Data;

@Data
public class ClientDto {

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private String login;

    private String password;

}
