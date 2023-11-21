package com.example.technologies.repository;

import com.example.technologies.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
