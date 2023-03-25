package com.dh.g1.apiwallet.repository;

import com.dh.g1.apiwallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findBytipoDocumentoAndNroDocumentoAndMoneda_Codigo(String tipoDocumento, String nroDocumento, String moneda);

    List<Wallet> findBytipoDocumentoAndNroDocumento(String tipoDocumento, String nroDocumento);
}
