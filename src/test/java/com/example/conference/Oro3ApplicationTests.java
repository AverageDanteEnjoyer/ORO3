package com.example.conference;

import com.example.conference.model.Participant;
import com.example.conference.model.Presentation;
import com.example.conference.model.Role;
import com.example.conference.model.Room;
import com.example.conference.repository.ParticipantRepository;
import com.example.conference.repository.PresentationRepository;
import com.example.conference.repository.RoomRepository;
import com.example.conference.service.ConferenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class ConferenceApplicationTests {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ConferenceService conferenceService;

    @BeforeEach
    void setup() {
        setupMockData();
    }

    void setupMockData() {
        Participant scientist = new Participant();
        scientist.setName("Scientist A");
        scientist.setCountry("USA");
        scientist.setRole(Role.SCIENTIST);
        participantRepository.save(scientist);

        Participant student = new Participant();
        student.setName("Student B");
        student.setCountry("UK");
        student.setRole(Role.STUDENT);
        participantRepository.save(student);

        Room room1 = new Room();
        room1.setName("Room 101");
        roomRepository.save(room1);

        Room room2 = new Room();
        room2.setName("Room 102");
        roomRepository.save(room2);

        Presentation presentation1 = new Presentation();
        presentation1.setTopic("Quantum Mechanics");
        presentation1.setRoom(room1);
        Set<Participant> presenters1 = new HashSet<>();
        presenters1.add(scientist);
        presentation1.setPresenters(presenters1);
        presentationRepository.save(presentation1);

        Presentation presentation2 = new Presentation();
        presentation2.setTopic("Machine Learning");
        presentation2.setRoom(room2);
        Set<Participant> presenters2 = new HashSet<>();
        presenters2.add(student);
        presentation2.setPresenters(presenters2);
        presentationRepository.save(presentation2);

        Presentation presentation3 = new Presentation();
        presentation3.setTopic("Astrophysics");
        presentation3.setRoom(room1);
        Set<Participant> presenters3 = new HashSet<>();
        presenters3.add(scientist);
        presentation3.setPresenters(presenters3);
        presentationRepository.save(presentation3);
    }

    @Test
    void testFindAllParticipants() {
        List<Participant> participants = conferenceService.getAllParticipants();
        assertNotNull(participants);
        assertEquals(2, participants.size());
    }

    @Test
    void testGetParticipantsByRole() {
        Map<Role, List<Participant>> participantsByRole = conferenceService.getParticipantsByRole();
        assertNotNull(participantsByRole);
        assertEquals(1, participantsByRole.get(Role.SCIENTIST).size());
        assertEquals(1, participantsByRole.get(Role.STUDENT).size());
    }

    @Test
    void testGetParticipantsByCountry() {
        Map<String, List<Participant>> participantsByCountry = conferenceService.getParticipantsByCountry();
        assertNotNull(participantsByCountry);
        assertEquals(1, participantsByCountry.get("USA").size());
        assertEquals(1, participantsByCountry.get("UK").size());
    }

    @Test
    void testFindAllPresentations() {
        List<Presentation> presentations = conferenceService.getAllPresentations();
        assertNotNull(presentations);
        assertEquals(3, presentations.size());
    }

    @Test
    void testGetParticipantWithMostPresentations() {
        Participant participantWithMostPresentations = conferenceService.getParticipantWithMostPresentations();
        assertNotNull(participantWithMostPresentations);
        assertEquals("Scientist A", participantWithMostPresentations.getName());
    }

    @Test
    void testGetPresentationCountByRoom() {
        Map<String, Long> presentationCountByRoom = conferenceService.getPresentationCountByRoom();
        assertNotNull(presentationCountByRoom);
        assertEquals(2, presentationCountByRoom.get("Room 101"));
        assertEquals(1, presentationCountByRoom.get("Room 102"));
    }
}