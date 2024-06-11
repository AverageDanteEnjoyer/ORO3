package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    @OneToMany(mappedBy = "room")
    private Set<Presentation> presentations;

    public void addPresentation(Presentation presentation) {
        if (presentations == null) {
            presentations = new HashSet<>();
        }
        presentations.add(presentation);
    }
}

