package com.example.TransactionalAnnotation.service;

import com.example.TransactionalAnnotation.model.Address;
import com.example.TransactionalAnnotation.model.Employee;
import com.example.TransactionalAnnotation.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Transactional(propagation = Propagation.SUPPORTS)
    public Address addAddress(Address address){
        addressRepository.save(address);
        return null;
    }
}
