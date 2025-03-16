package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
/*@AllArgsConstructor
@NoArgsConstructor*/
@RequiredArgsConstructor
@Table(name = "calendar", schema = "prod")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", unique = true, nullable = false)
    private LocalDate date;

    /*@OneToMany(mappedBy = "calendar", fetch = FetchType.EAGER)
    private List<Record> records = new ArrayList<>();*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "master_calendar",
            joinColumns = @JoinColumn(name = "calendar_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "master_id", referencedColumnName = "id"))
    private List<Master> masters = new ArrayList<>();

}
