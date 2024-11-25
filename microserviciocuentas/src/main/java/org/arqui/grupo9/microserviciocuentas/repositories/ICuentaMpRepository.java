package org.arqui.grupo9.microserviciocuentas.repositories;

import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaMpRepository extends JpaRepository<CuentaMP, Long> {
}
