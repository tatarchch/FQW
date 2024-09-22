package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Data
@Table(name = "record", schema = "public")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "time")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

}
