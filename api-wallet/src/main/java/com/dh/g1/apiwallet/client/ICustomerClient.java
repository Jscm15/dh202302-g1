package com.dh.g1.apiwallet.client;

import com.dh.g1.apiwallet.config.LoadBalancerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Optional;

@FeignClient(name="api-customer")
@LoadBalancerClient(value = "api-customer", configuration = LoadBalancerConfiguration.class)
public interface ICustomerClient {

    @GetMapping("/api/v1/customers/{doctype}/{docnum}")
    Optional<CustomerDto> getCustomer (@PathVariable String doctype, @PathVariable String docnum);

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    class CustomerDto{
        private Long id;
        private String tipoDocumento;
        private String nroDocumento;
        private String nombre;
        private String apellido;
        private String genero;
        private LocalDate fechaNacimiento;
    }
}
