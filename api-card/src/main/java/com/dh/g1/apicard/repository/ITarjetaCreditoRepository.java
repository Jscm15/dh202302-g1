package com.dh.g1.apicard.repository;

import com.dh.g1.apicard.model.TarjetaCredito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarjetaCreditoRepository extends MongoRepository<TarjetaCredito, String> {
}
