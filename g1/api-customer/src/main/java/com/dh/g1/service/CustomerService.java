package com.dh.g1.service;

import com.dh.g1.model.Customer;
import com.dh.g1.model.CustomerDto;
import com.dh.g1.repository.ICustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerService implements ICustomerService{


    private final ICustomerRepository customerRepository;
    private final ObjectMapper mapper;

    public CustomerService(ICustomerRepository customerRepository, ObjectMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        customerRepository.save(mapper.convertValue(customerDto,Customer.class));
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    @Transactional
    public Set<CustomerDto> readCustomers() {
        List<Customer> customers = customerRepository.findAll();
        Set<CustomerDto> customerDtos = new HashSet<>();

        for (Customer customer:customers) {
            customerDtos.add(mapper.convertValue(customer, CustomerDto.class));
        }
        return customerDtos;
    }

    @Override
    @Transactional
    public Optional<Customer> readCustomer(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {

    }
}
