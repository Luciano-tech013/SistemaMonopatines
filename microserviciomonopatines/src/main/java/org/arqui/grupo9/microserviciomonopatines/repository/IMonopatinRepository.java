package org.arqui.grupo9.microserviciomonopatines.repository;

import org.arqui.grupo9.microserviciomonopatines.model.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMonopatinRepository extends JpaRepository<Monopatin, Long> {

    @Query("SELECT m FROM Monopatin m WHERE m.estado = :estado")
    List<Monopatin> findAllEstadoMonopatines(String estado);

    @Query("SELECT m FROM Monopatin m WHERE m.latitud >= :latitud AND m.longitud <= :longitud")
    List<Monopatin> findAllByUbicacion(double latitud, double longitud);

    @Query("SELECT m FROM Monopatin m WHERE m.estado = 'operacion' ORDER BY m.kmsRecorridos DESC")
    List<Monopatin> findAllByUso();

}
