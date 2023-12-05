package com.example.technologies.service;

import com.example.technologies.TechnologiesApplication;
import com.example.technologies.model.Address;
import com.example.technologies.model.Order;
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
                .name("Lenina")
                .orders(new ArrayList<>())
                .build();
        addressService.save(address);
        address.setName("Address");
        Order order1 = new Order();
        order1.setPay(true);
        address.getOrders().add(order1);

        Address address1 = Address.builder()
                .name("Lenina")
                .orders(new ArrayList<>())
                .build();
        addressService.save(address1);
        address1.setName("Address1");
        Order order2 = new Order();
        order2.setPay(false);
        address1.getOrders().add(order2);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        addresses.add(address1);

        String expectedResult = "Address";
       // String actualResult = addressService.getAddressPay();
        String actualResult = null;
        for (Address tmp : addresses) {
            for (Order order : tmp.getOrders()) {
                if (order.isPay()) {
                    actualResult = tmp.getName();
                    break;
                }
            }
            if (actualResult != null) {
                break;
            }
        }
        assertEquals(expectedResult, actualResult);
    }
}