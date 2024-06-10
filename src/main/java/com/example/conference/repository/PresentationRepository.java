package com.example.conference.repository;

import com.example.conference.model.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    List<Presentation> findByPresenters_Id(Long presenterId);
}
