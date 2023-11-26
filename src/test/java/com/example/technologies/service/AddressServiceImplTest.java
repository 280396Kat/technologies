package com.example.technologies.service;

import com.example.technologies.TechnologiesApplication;
import com.example.technologies.model.Address;
import com.example.technologies.model.Candidate;
import com.example.technologies.model.Order;
import com.example.technologies.model.Technology;
import com.example.technologies.repository.AddressRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = TechnologiesApplication.class)
class AddressServiceImplTest {
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private AddressRepository addressRepository;

    @AfterEach
    void clean() {
        addressRepository.deleteAll();
    }

    @Test
    void save() {
        Address address = Address.builder()
                .name("Lenina")
                .build();
        Address expected = Address.builder()
                .id(1L)
                .name("Lenina")
                .orders(new ArrayList<>())
                .build();
        Address result = addressService.save(address);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getAddressPay() {
        Address address = Address.builder()
                .id(1L)
                .name("Lenina")
                .orders(new ArrayList<>())
                .build();
        String expected = String.valueOf(addressService.save(address));
        String result = addressService.getAddressPay();
        Assertions.assertEquals(expected, result);
    }
}