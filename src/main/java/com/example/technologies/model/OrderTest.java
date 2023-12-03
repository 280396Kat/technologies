package com.example.technologies.model;

import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderTest {

    private Long id;

    private String name;

    private boolean isPay;

    private User user;

}
