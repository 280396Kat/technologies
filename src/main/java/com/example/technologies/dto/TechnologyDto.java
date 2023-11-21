package com.example.technologies.dto;

import com.example.technologies.model.Candidate;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDto {

    private Long id;

    private String name;

    private int level;
}
