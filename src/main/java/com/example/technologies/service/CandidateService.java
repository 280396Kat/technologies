package com.example.technologies.service;

import com.example.technologies.dto.CandidateDto;
import com.example.technologies.dto.CandidateWithTechnologyDto;
import com.example.technologies.dto.TechnologyDto;
import com.example.technologies.model.Candidate;
import com.example.technologies.model.Technology;
import com.example.technologies.repository.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService implements CandidateServiceImpl{

    private final CandidateRepository candidateRepository;
   @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
       this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate save(Candidate candidate) {
       return candidateRepository.save(candidate);
    }

    @Override
    public Candidate update(Long id, Candidate candidate) {
        Candidate getCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Кандидат не найден"));
        getCandidate.setName(candidate.getName());
        getCandidate.setTechnologies(candidate.getTechnologies());
        return candidateRepository.save(getCandidate);
    }

    @Override
    public boolean deleteById(Long id) {
       Optional<Candidate> getCandidate = candidateRepository.findById(id);
       if (getCandidate.isEmpty()) {
           return false;
       }
        candidateRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CandidateDto> getAll() {
        return candidateRepository.findAll().stream()
                .map(candidate -> CandidateDto.builder()
                        .id(candidate.getId())
                        .name(candidate.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateWithTechnologyDto> getAllCandidateWithTechnology() {
        List<Candidate> candidatesList = candidateRepository.findAll();
        List<CandidateWithTechnologyDto> candidateWithTechnologyDtos = new ArrayList<>();
        List<TechnologyDto> technologyDtoList = new ArrayList<>();
        for (Candidate tmp : candidatesList) {
            CandidateWithTechnologyDto candidateDto1 = new CandidateWithTechnologyDto();
            List<Technology> technologyList = tmp.getTechnologies();
            for (Technology technology : technologyList) {
                TechnologyDto technologyDto = new TechnologyDto();
                technologyDto.setName(technology.getName());
                technologyDto.setLevel(technology.getLevel());
                technologyDto.setId(technology.getId());
                technologyDtoList.add(technologyDto);
            }
            candidateDto1.setName(tmp.getName());
            candidateDto1.setId(tmp.getId());
            candidateDto1.setTechnologyDto(technologyDtoList);
            candidateWithTechnologyDtos.add(candidateDto1);
        }
        return candidateWithTechnologyDtos;
    }
}
