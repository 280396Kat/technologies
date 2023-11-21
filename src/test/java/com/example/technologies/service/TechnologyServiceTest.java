package com.example.technologies.service;

import com.example.technologies.TechnologiesApplication;
import com.example.technologies.dto.TechnologyDto;
import com.example.technologies.dto.TechnologyWithCandidateDto;
import com.example.technologies.model.Candidate;
import com.example.technologies.model.Technology;
import com.example.technologies.repository.TechnologyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TechnologiesApplication.class)
class TechnologyServiceTest {
    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private TechnologyService technologyService;

    @AfterEach
    void clean() {
        technologyRepository.deleteAll();
    }

    @Test
    void save() {
        Technology technology = Technology.builder()
                .name("Hibernate")
                .level(1)
                .build();
        Technology expected = Technology.builder()
                .id(1L)
                .name("Hibernate")
                .level(1)
                .candidate(new Candidate())
                .build();
        Technology result = technologyService.save(technology);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void update() {
        Technology technology = Technology.builder()
                .name("Hibernate")
                .level(1)
                .candidate(new Candidate())
                .build();
        Technology expected = technologyService.save(technology);
        expected.setLevel(2);
        Technology result = technologyService.update(1L, expected);
        Assertions.assertEquals(2, result.getLevel());
    }

    @Test
    void delete() {
        Technology technology = Technology.builder()
                .id(1L)
                .name("Hibernate")
                .level(1)
                .candidate(new Candidate())
                .build();
        technologyService.save(technology);
        boolean result = technologyService.deleteById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void getAll() {
        Technology technology = Technology.builder()
                .id(1L)
                .name("Hibernate")
                .level(1)
                .candidate(new Candidate())
                .build();
        Technology expected = technologyService.save(technology);
        List<TechnologyDto> result = technologyService.getAll();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(expected.getId(), result.get(0).getId());
    }

    @Test
    void getAllCandidateWithTechnology() {
        Candidate candidate = Candidate.builder()
                .name("Fedor")
                .build();
        Technology technology = Technology.builder()
                .name("Hibernate")
                .level(1)
                .candidate(new Candidate())
                .build();
        Technology expected = technologyService.save(technology);
        List<TechnologyWithCandidateDto> result = technologyService.getAllTechnologyWithCandidateDto();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(expected.getId(), result.get(0).getId());

    }
}