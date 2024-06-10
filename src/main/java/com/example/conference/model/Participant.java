package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Participant {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String country;
    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;
}

