package com.example.technologies.service;

import com.example.technologies.TechnologiesApplication;
import com.example.technologies.dto.CandidateDto;
import com.example.technologies.dto.CandidateWithTechnologyDto;
import com.example.technologies.dto.TechnologyDto;
import com.example.technologies.model.Candidate;
import com.example.technologies.model.Technology;
import com.example.technologies.repository.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TechnologiesApplication.class)
class CandidateServiceTest {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CandidateService candidateService;

    @AfterEach
    void clean() {
        candidateRepository.deleteAll();
    }

    @Test
    void save() {
        Candidate candidate = Candidate.builder()
                .name("Gena")
                .build();
        Candidate expected = Candidate.builder()
                .id(1L)
                .name("Gena")
                .build();
        Candidate result = candidateService.save(candidate);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void update() {
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("Gena")
                .technologies(new ArrayList<>())
                .build();
        Candidate expected = candidateService.save(candidate);
        Candidate result = candidateService.update(1L, expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void deleteById() {
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("Gena")
                .technologies(new ArrayList<>())
                .build();
        candidateService.save(candidate);
        boolean result = candidateService.deleteById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void getAll() {
        Candidate candidate = Candidate.builder()
                .id(1L)
                .name("Gena")
                .technologies(new ArrayList<>())
                .build();
        Candidate expected = candidateService.save(candidate);
        List<CandidateDto> result = candidateService.getAll();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(expected.getId(), result.get(0).getId());
    }

    @Test
    void getAllCandidateWithTechnology() {
        Technology technology = Technology.builder()
                .level(1)
                .name("Java")
                .build();
        Candidate candidate = Candidate.builder()
                .name("Gena")
                .technologies(List.of(technology))
                .build();
        Candidate expected = candidateService.save(candidate);
        List<CandidateWithTechnologyDto> result = candidateService.getAllCandidateWithTechnology();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(expected.getId(), result.get(0).getId());
    }
}