package com.example.fqw.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
/*@AllArgsConstructor
@NoArgsConstructor*/
@RequiredArgsConstructor
@Table(name = "admin", schema = "prod")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "login", nullable = false)
    private String login;
}
