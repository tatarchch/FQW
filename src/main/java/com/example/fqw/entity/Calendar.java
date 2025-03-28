package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "calendar", schema = "prod")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", unique = true, nullable = false)
    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "master_calendar",
            joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "master_id", referencedColumnName = "id"))
    private List<Master> masters = new ArrayList<>();

}
