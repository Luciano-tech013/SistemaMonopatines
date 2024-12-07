package org.arqui.grupo9.microserviciocuentas.repositories;

import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICuentaSistemaRepository extends JpaRepository <CuentaSistema, Long> {

    @Query("SELECT c FROM CuentaSistema c WHERE c.username = :username")
    Optional<CuentaSistema> findByUsername(String username);
}
