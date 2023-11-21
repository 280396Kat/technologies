package com.example.technologies.controller;

import com.example.technologies.dto.CandidateDto;
import com.example.technologies.dto.CandidateWithTechnologyDto;
import com.example.technologies.model.Candidate;
import com.example.technologies.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate save(@RequestBody Candidate candidate) {
        return candidateService.save(candidate);
    }

    @PutMapping("/update/{id}")
    public Candidate update(@PathVariable Long id, @RequestBody Candidate candidate) {
        return candidateService.update(id, candidate);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteById(Long id) {
        return candidateService.deleteById(id);
    }

    @GetMapping("/getAll")
    public List<CandidateDto> getAll() {
        return candidateService.getAll();
    }

    @GetMapping("/getAllCandidateWithTechnology")
    public List<CandidateWithTechnologyDto> getAllCandidateWithTechnology() {
        return candidateService.getAllCandidateWithTechnology();
    }
}
