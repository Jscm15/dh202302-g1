package com.dh.g1.service;

import com.dh.g1.exceptions.CustomerException;
import com.dh.g1.exceptions.ResourceNotFoundException;
import com.dh.g1.model.CustomerDto;

import java.util.Optional;
import java.util.Set;

public interface ICustomerService {

    void createCustomer(CustomerDto customerDto);
    Optional<CustomerDto> updateCustomer (Long id, CustomerDto customerDto);
    Set<CustomerDto> readCustomers ();
    Optional<CustomerDto> readCustomer (Long id);
    Optional<CustomerDto> getCustomer (String tipoDocumento, String numDocumento) throws CustomerException;
    void deleteCustomer (Long id);

}
