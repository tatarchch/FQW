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
@Table(name = "master", schema = "prod")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    /*@Column(name = "surname", nullable = false)
    private String surname;*/

    /*@Column(name = "patronymic")
    private String patronymic;*/

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
