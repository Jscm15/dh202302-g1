package com.dh.g1.controller;

import com.dh.g1.exceptions.CustomerException;
import com.dh.g1.exceptions.InvalidDataResource;
import com.dh.g1.exceptions.ResourceNotFoundException;
import com.dh.g1.model.CustomerDto;
import com.dh.g1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<Collection<CustomerDto>> readCustomerByTypeAndNumber (@RequestParam(value = "type", required = true) String tipoDocumento,
                                                                                @RequestParam(value = "number", required = true) String nroDocumento) {

        return ResponseEntity.ok(customerService.readCustomerByTypeAndNumber(tipoDocumento, nroDocumento));
    }

    @GetMapping("/{doctype}/{docnum}")
    public Optional<CustomerDto> getCustomer (@PathVariable String doctype, @PathVariable String docnum) throws CustomerException {
        return customerService.getCustomer(doctype, docnum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<CustomerDto>> updateCustomer (@PathVariable Long id, @RequestBody CustomerDto customerDto) throws ResourceNotFoundException{
        customerService.readCustomer(id).orElseThrow(()-> {
            return new ResourceNotFoundException(String.format("Customer with id %s not found",id));
        });

        return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer (@PathVariable Long id) throws ResourceNotFoundException, InvalidDataResource {

        if (id <= 0) {
            throw new InvalidDataResource("Enter a valid id");
        }

        CustomerDto customerDto = customerService.readCustomer(id).orElseThrow(() -> {
            return new ResourceNotFoundException(String.format("Category with id %s not found", id));
        });

        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    }
