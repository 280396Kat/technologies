package com.example.technologies.controller;

import com.example.technologies.dto.TechnologyDto;
import com.example.technologies.dto.TechnologyWithCandidateDto;
import com.example.technologies.model.Technology;
import com.example.technologies.service.TechnologyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/technology")
@RestController
public class TechnologyController {

    private final TechnologyServiceImpl technologyService;

    @Autowired
    public TechnologyController(TechnologyServiceImpl technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Technology save(@RequestBody Technology technology) {
        return technologyService.save(technology);
    }

    @PutMapping("/update/{id}")
    public Technology update(@PathVariable Long id, @RequestBody Technology technology) {
        return technologyService.update(id, technology);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id) {
        technologyService.deleteById(id);
    }

    @GetMapping("/getAll")
    public List<TechnologyDto> getAll() {
        return technologyService.getAll();
    }

    @GetMapping("/getAllCandidateWithTechnology")
    public List<TechnologyWithCandidateDto> getAllTechnologyWithCandidateDto() {
        return technologyService.getAllTechnologyWithCandidateDto();
    }
}
