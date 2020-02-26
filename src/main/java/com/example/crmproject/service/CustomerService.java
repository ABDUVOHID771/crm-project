package com.example.crmproject.service;

import com.example.crmproject.domain.Customer;
import com.example.crmproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }

    public void deleteById(Long id) {
        this.customerRepository.deleteById(id);
    }
}
