package com.example.FQW.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<Record> records = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "place_service",
            joinColumns = @JoinColumn(name = "place_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> services = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "master_place",
            joinColumns = @JoinColumn(name = "place_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "master_id", referencedColumnName = "id"))
    private List<Master> masters = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "place_calendar",
            joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "place_id", referencedColumnName = "id"))
    private List<Calendar> calendars = new ArrayList<>();


}
