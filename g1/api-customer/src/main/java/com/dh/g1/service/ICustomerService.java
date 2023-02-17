package com.dh.g1.service;

import com.dh.g1.model.Customer;
import com.dh.g1.model.CustomerDto;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer (CustomerDto customerDto);
    Set<CustomerDto> readCustomers ();
    Optional<Customer> readCustomer (Long id);
    void deleteCustomer (Long id);

}
