package org.arqui.grupo9.microservicioestaciones.service;

import org.arqui.grupo9.microservicioestaciones.client.MonopatinFeignClient;
import org.arqui.grupo9.microservicioestaciones.model.Estacion;
import org.arqui.grupo9.microservicioestaciones.model.dtos.MonopatinDTO;
import org.arqui.grupo9.microservicioestaciones.repository.IEstacionRepository;
import org.arqui.grupo9.microservicioestaciones.service.exception.NotFoundEstacionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionService {

    private IEstacionRepository estacionRepository;
    private MonopatinFeignClient monopatinClient;

    public EstacionService(IEstacionRepository estacionRepository, @Lazy MonopatinFeignClient monopatinClient) {
        this.estacionRepository = estacionRepository;
        this.monopatinClient = monopatinClient;
    }

    public List<Estacion> findAll() {
        return this.estacionRepository.findAll();
    }

    public Estacion findById(String id) {
        Optional<Estacion> estacion = this.estacionRepository.findById(id);
        if(estacion.isPresent())
            return estacion.get();

        throw new NotFoundEstacionException("La estacion con ese id no esta cargada en el sistema", "No se encontro la estacion con ese id solicitado. Por favor, verfica que este cargada en el sistema", "low");
    }

    public boolean save(Estacion estacion) {
        this.estacionRepository.save(estacion);
        return true;
    }

    public boolean deleteById(String id) {
        this.findById(id);
        this.estacionRepository.deleteById(id);
        return true;
    }

    public boolean updateById(String id, Estacion eModified) {
        Estacion estacion = this.findById(id);

        estacion.set_id(eModified.get_id());
        estacion.setNombre(eModified.getNombre());
        estacion.setLatitud(eModified.getLatitud());
        estacion.setLongitud(eModified.getLongitud());
        return this.save(estacion);
    }

    public List<Estacion> getEstacionesByUbicacion(Double latitud, Double longitud) {
        return this.estacionRepository.getEstacionsByUbicacion(latitud, (latitud + 100), longitud, (longitud + 100));
    }

    public List<MonopatinDTO> getMonopatinesByUbicacion(double latitud, double longitud) {
        return this.monopatinClient.getMonopatinesByUbicacion(latitud, longitud).getBody();
    }

}
