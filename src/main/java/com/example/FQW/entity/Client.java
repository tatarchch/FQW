package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//@ToString(exclude = "id")
@Table(name = "client", schema = "public")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
}
