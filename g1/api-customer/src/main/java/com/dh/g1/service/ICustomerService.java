package com.dh.g1.service;

import com.dh.g1.model.Customer;
import com.dh.g1.model.CustomerDto;

import java.util.Set;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer (Customer customer, CustomerDto customerDto);
    Set<CustomerDto> readCustomers ();
    CustomerDto readCustomer (Long id);
    void deleteCustomer (Long id);

}
