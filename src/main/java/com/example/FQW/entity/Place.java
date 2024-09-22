package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "place", schema = "public")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
}
