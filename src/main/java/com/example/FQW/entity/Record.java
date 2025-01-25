package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "record", schema = "prod")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "timing")
    private String timing;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /*@ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;*/

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;


}
