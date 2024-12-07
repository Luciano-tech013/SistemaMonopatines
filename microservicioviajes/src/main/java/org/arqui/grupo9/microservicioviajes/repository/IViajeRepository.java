package org.arqui.grupo9.microservicioviajes.repository;

import org.arqui.grupo9.microservicioviajes.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IViajeRepository extends JpaRepository<Viaje, Long> {
    @Query("SELECT v.idMonopatin FROM Viaje v WHERE YEAR(v.fechaFinViaje) = :anio GROUP BY v.idMonopatin HAVING COUNT(v.idViaje) > :cantViajes")
    List<Long> getMonopatinesByCantViajesInOneYear(int anio, int cantViajes);

    @Query("SELECT SUM(v.costoTotal) FROM Viaje v WHERE MONTH(v.fechaFinViaje) >= :mesIni AND MONTH(v.fechaFinViaje) <= :mesFin AND YEAR(v.fechaFinViaje) = :anio")
    Double getTotalFacturadoByMesesInOneYear(int mesIni, int mesFin, int anio);

    @Query("SELECT v FROM Viaje v WHERE v.idMonopatin = :idMonopatin")
    List<Viaje> getTiemposPausadosDeMonopatin(Long idMonopatin);
}
