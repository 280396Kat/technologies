package com.example.technologies.service;

import com.example.technologies.dto.CandidateDto;
import com.example.technologies.dto.CandidateWithTechnologyDto;
import com.example.technologies.model.Candidate;

import java.util.List;

public interface CandidateServiceImpl {

    Candidate save(Candidate candidate);

    Candidate update(Long id, Candidate candidate);

    boolean deleteById(Long id);

    List<CandidateDto> getAll();

    List<CandidateWithTechnologyDto> getAllCandidateWithTechnology();

}
