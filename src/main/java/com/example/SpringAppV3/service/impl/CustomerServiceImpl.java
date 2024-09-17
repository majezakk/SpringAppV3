package com.example.SpringAppV3.service.impl;

import com.example.SpringAppV3.model.CustomerModel;
import com.example.SpringAppV3.repository.CustomerRepository;
import com.example.SpringAppV3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerModel> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public CustomerModel saveCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}