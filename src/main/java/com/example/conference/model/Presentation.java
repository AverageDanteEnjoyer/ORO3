package com.example.conference.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
public class Presentation {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String topic;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "presentation_participant",
            joinColumns = @JoinColumn(name = "presentation_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> presenters;
}

