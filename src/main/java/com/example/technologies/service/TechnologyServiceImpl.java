package com.example.technologies.service;

import com.example.technologies.dto.CandidateWithTechnologyDto;
import com.example.technologies.dto.TechnologyDto;
import com.example.technologies.dto.TechnologyWithCandidateDto;
import com.example.technologies.model.Technology;

import java.util.List;

public interface TechnologyServiceImpl {

    Technology save(Technology technology);

    Technology update(Long id, Technology technology);

    boolean deleteById(Long id);

    List<TechnologyDto> getAll();

    List<TechnologyWithCandidateDto> getAllTechnologyWithCandidateDto();

}
