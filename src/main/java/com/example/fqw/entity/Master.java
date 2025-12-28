package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "master", schema = "prod")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "master_seq")
    @SequenceGenerator(
            name = "master_seq",
            sequenceName = "master_seq",
            schema = "prod",
            allocationSize = 1
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "active")
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "master", fetch = FetchType.EAGER)
    private List<Record> records = new ArrayList<>();

}
