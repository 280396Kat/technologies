package com.example.technologies.service;

import com.example.technologies.dto.CandidateDto;
import com.example.technologies.dto.TechnologyDto;
import com.example.technologies.dto.TechnologyWithCandidateDto;
import com.example.technologies.model.Candidate;
import com.example.technologies.model.Technology;
import com.example.technologies.repository.TechnologyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public Technology save(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public Technology update(Long id, Technology technology) {
        Technology getTechnology = technologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Технология не найдена."));
        getTechnology.setName(technology.getName());
        getTechnology.setLevel(technology.getLevel());
        getTechnology.setCandidate(technology.getCandidate());
        return technologyRepository.save(getTechnology);
    }

    @Override
    public boolean deleteById(Long id) {
        //Technology technology = technologyRepository.findById(id)
        //        .orElseThrow(() -> new EntityNotFoundException("Технология не найдена"));
        //technologyRepository.delete(technology);
        Optional<Technology> getTechnology = technologyRepository.findById(id);
        if (getTechnology.isEmpty()) {
            return false;
        }
        technologyRepository.deleteById(id);
        return true;
    }


    @Override
    public List<TechnologyDto> getAll() {
        List<Technology> technologyList = technologyRepository.findAll();
        List<TechnologyDto> technologyDtoList = new ArrayList<>();
        for (Technology tmp : technologyList) {
            TechnologyDto technologyDto = new TechnologyDto();
            technologyDto.setId(tmp.getId());
            technologyDto.setName(tmp.getName());
            technologyDto.setLevel(tmp.getLevel());
            technologyDtoList.add(technologyDto);
        }
        return technologyDtoList;
    }

    @Override
    public List<TechnologyWithCandidateDto> getAllTechnologyWithCandidateDto() {
        List<Technology> technologyList = technologyRepository.findAll();
        List<TechnologyWithCandidateDto> technologyWithCandidateDtos = new ArrayList<>();
        List<CandidateDto> candidateDtoList = new ArrayList<>();
        for (Technology tmp : technologyList) {
            TechnologyWithCandidateDto technologyWithCandidateDto = new TechnologyWithCandidateDto();
            CandidateDto candidateDto = new CandidateDto();
            Candidate candidate = tmp.getCandidate();
            candidateDto.setName(candidate.getName());
            candidateDto.setId(candidate.getId());
            candidateDtoList.add(candidateDto);
            technologyWithCandidateDto.setId(tmp.getId());
            technologyWithCandidateDto.setName(tmp.getName());
            technologyWithCandidateDto.setCandidateDtoList(candidateDtoList);
        }
            return technologyWithCandidateDtos;
        }
    }


