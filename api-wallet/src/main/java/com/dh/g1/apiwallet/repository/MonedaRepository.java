package com.dh.g1.apiwallet.repository;

import com.dh.g1.apiwallet.model.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonedaRepository extends JpaRepository <Moneda,String> {
}
