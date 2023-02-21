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
    public CustomerDto updateCustomer(Customer customer, CustomerDto customerDto) {
        if(customerDto.getNombre() != null){
            customer.setNombre(customerDto.getNombre());
        }
        if(customerDto.getApellido() != null){
            customer.setApellido(customerDto.getApellido());
        }
        if(customerDto.getGenero() != null){
            customer.setGenero(customerDto.getGenero());
        }
        if(customerDto.getFechaNacimiento() != null){
            customer.setFechaNacimiento(customerDto.getFechaNacimiento());
        }
        return mapper.convertValue(customerRepository.save(customer), CustomerDto.class);
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
    public CustomerDto readCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return mapper.convertValue(customer, CustomerDto.class);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.findById(id);
    }
}
