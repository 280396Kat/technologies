package com.example.technologies.service;

import com.example.technologies.model.Address;
import com.example.technologies.model.Order;
import com.example.technologies.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public String getAddressPay() {
        String name = null;
        List<Address> addresses = addressRepository.findAll();
        for (Address tmp : addresses) {
           List<Order> orders = tmp.getOrders();
           for (Order orderTmp : orders) {
               boolean isPay = orderTmp.isPay();
               if (isPay) {
                   name = tmp.getName();
               }
           }
        }
        return name;
    }
}
