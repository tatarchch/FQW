package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
/*@AllArgsConstructor
@NoArgsConstructor*/
@RequiredArgsConstructor
@Table(name = "place", schema = "prod")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<Master> masters = new ArrayList<>();

    /*@OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<Record> records = new ArrayList<>();*/


}
