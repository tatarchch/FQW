package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "pet_service", schema = "prod")
public class PetService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "level")
    private Integer level;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "petService", fetch = FetchType.LAZY)
    private List<Record> records = new ArrayList<>();

}
