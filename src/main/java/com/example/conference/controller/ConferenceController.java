package com.example.conference.controller;

import com.example.conference.model.Participant;
import com.example.conference.model.Presentation;
import com.example.conference.model.Role;
import com.example.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        return conferenceService.getAllParticipants();
    }

    @GetMapping("/participants/role")
    public Map<Role, List<Participant>> getParticipantsByRole() {
        return conferenceService.getParticipantsByRole();
    }

    @GetMapping("/participants/country")
    public Map<String, List<Participant>> getParticipantsByCountry() {
        return conferenceService.getParticipantsByCountry();
    }

    @GetMapping("/presentations")
    public List<Presentation> getAllPresentations() {
        return conferenceService.getAllPresentations();
    }

    @GetMapping("/participant/most-presentations")
    public Participant getParticipantWithMostPresentations() {
        return conferenceService.getParticipantWithMostPresentations();
    }

    @GetMapping("/presentations/room-count")
    public Map<String, Long> getPresentationCountByRoom() {
        return conferenceService.getPresentationCountByRoom();
    }
}
