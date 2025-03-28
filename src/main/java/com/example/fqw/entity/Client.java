package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "client", schema = "prod")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "chat_id", unique = true)
    private String chatId;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Record> records = new ArrayList<>();

}
