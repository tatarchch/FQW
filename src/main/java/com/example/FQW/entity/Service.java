package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "service", schema = "public")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private Integer cost;
}
