package org.arqui.grupo9.microservicioestaciones.repository;

import org.arqui.grupo9.microservicioestaciones.model.Estacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IEstacionRepository extends MongoRepository<Estacion, String> {

    /*@Query("SELECT e FROM Estacion e WHERE e.latitud >= :latitud AND e.longitud <= :longitud")
    public List<Estacion> getEstacionsByUbicacion(Double latitud, Double longitud);*/

    @Query("{'id_estacion' :  ?0}")
    Optional<Estacion> findByIdEstacion(String idEstacion);
}
