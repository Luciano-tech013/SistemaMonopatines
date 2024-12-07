package org.arqui.grupo9.serviciomercadopago.repository;

import org.arqui.grupo9.serviciomercadopago.model.CuentaMP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICuentaMPRepository extends MongoRepository<CuentaMP, Long> {
}
