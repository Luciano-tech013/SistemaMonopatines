package org.arqui.grupo9.microservicioestaciones.repository;

import org.arqui.grupo9.microservicioestaciones.model.Estacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface IEstacionRepository extends MongoRepository<Estacion, String> {

    @Query("{ 'latitud': { $gte: ?0, $lte: ?1 }, 'longitud': { $gte: ?2, $lte: ?3 } }")
    List<Estacion> getEstacionsByUbicacion(Double minLatitud, Double maxLatitud, Double minLongitud, Double maxLongitud);
}
