package com.example.technologies.dto;

import com.example.technologies.model.Candidate;
import com.example.technologies.model.Technology;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateWithTechnologyDto {

    private Long id;

    private String name;

    private List<TechnologyDto> technologyDto;
}
