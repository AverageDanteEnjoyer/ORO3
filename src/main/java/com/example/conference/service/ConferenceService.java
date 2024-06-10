package com.example.conference.service;

import com.example.conference.model.Participant;
import com.example.conference.model.Presentation;
import com.example.conference.model.Role;
import com.example.conference.model.Room;
import com.example.conference.repository.ParticipantRepository;
import com.example.conference.repository.PresentationRepository;
import com.example.conference.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConferenceService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Map<Role, List<Participant>> getParticipantsByRole() {
        return participantRepository.findAll().stream().collect(Collectors.groupingBy(Participant::getRole));
    }

    public Map<String, List<Participant>> getParticipantsByCountry() {
        return participantRepository.findAll().stream().collect(Collectors.groupingBy(Participant::getCountry));
    }

    public List<Presentation> getAllPresentations() {
        return presentationRepository.findAll();
    }

    public Participant getParticipantWithMostPresentations() {
        return participantRepository.findAll().stream()
                .max((p1, p2) -> Integer.compare(
                        presentationRepository.findByPresenters_Id(p1.getId()).size(),
                        presentationRepository.findByPresenters_Id(p2.getId()).size()
                )).orElse(null);
    }

    public Map<String, Long> getPresentationCountByRoom() {
        return roomRepository.findAll().stream().collect(Collectors.toMap(
                Room::getName,
                room -> (long) room.getPresentations().size()
        ));
    }
}

