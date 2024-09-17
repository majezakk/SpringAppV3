package com.example.SpringAppV3.service;

import com.example.SpringAppV3.model.CustomerModel;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerModel> getAllCustomers();
    Optional<CustomerModel> getCustomerById(Long id);
    CustomerModel saveCustomer(CustomerModel customer);
    void deleteCustomer(Long id);
}