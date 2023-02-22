package com.dh.g1.repository;

import com.dh.g1.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Query(value = "UPDATE db_virtualwallet.customers SET customers.se_borro=true WHERE id = ?", nativeQuery = true)
    void softDelete(Long Id);

    @Query(value = "Select * FROM db_virtualwallet.customers  WHERE customers.tipo_documento = ?1 AND customers.nro_documento = ?2 AND customers.se_borro=false", nativeQuery = true)
    List<Customer> findByTypeAndNumber(String tipoDocumento,String nroDocumento);
}
