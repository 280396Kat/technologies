package com.example.technologies.model;

import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserTest {

    private Long id;

    private String name;

    private List<OrderTest> orders;
}
