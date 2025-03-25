package com.example.fqw.dto;

import lombok.Data;

@Data
public class ClientDto {

    private Long id;

    private String name;

    private String login;

    private String password;

    private String email;

    private String role;

    private String chatId;

}
