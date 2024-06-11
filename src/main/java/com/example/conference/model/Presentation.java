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

    @Getter
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public void setRoom(Room room) {
        this.room = room;
        if (room != null) {
            room.addPresentation(this);
        }
    }

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

