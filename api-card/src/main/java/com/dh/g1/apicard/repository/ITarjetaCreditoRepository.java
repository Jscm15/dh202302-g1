package com.dh.g1.apicard.repository;

import com.dh.g1.apicard.model.TarjetaCredito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITarjetaCreditoRepository extends MongoRepository<TarjetaCredito, String> {
    Optional<TarjetaCredito> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
}
