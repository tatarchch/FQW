package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "master", schema = "public")
public class Master {
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
    @Column(name = "level")
    private Integer level;
}
