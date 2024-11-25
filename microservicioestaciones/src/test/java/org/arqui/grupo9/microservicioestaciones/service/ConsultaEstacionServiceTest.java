package org.arqui.grupo9.microservicioestaciones.service;

import org.arqui.grupo9.microservicioestaciones.model.Estacion;
import org.arqui.grupo9.microservicioestaciones.repository.IEstacionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConsultaEstacionServiceTest {
    @Autowired
    private EstacionService service;

    @Autowired
    private IEstacionRepository repository;

    @Test
    @DisplayName("Test para obtener un listado de las estaciones cerca del usuario")
    void testGetEstacionesByUbicacion() {
        double latitud = 200;
        double longitud = 150;

        //buscar estaciones entre x = 200 e y = 150
        Estacion e1 = new Estacion("1", "Estacion 1", 208.07, 149.40);
        Estacion e2 = new Estacion("2", "Estacion 2", 201.23, 160.70);
        Estacion e3 = new Estacion("3", "Estacion 3", 198.60, 347.20);
        Estacion e4 = new Estacion("4", "Estacion 4", 223.70, 121.20);
        Estacion e5 = new Estacion("5", "Estacion 5", 340.12, 149.98);
        Estacion e6 = new Estacion("6", "Estacion 6", 78.56, 123.56);

        List<Estacion> estacionesEsperadas = List.of(e1, e4, e5);

        this.service.save(e1);
        this.service.save(e2);
        this.service.save(e3);
        this.service.save(e4);
        this.service.save(e5);
        this.service.save(e6);

        List<Estacion> resultado = this.service.getEstacionesByUbicacion(latitud, longitud);

        assertEquals(estacionesEsperadas.size(), resultado.size());
        assertEquals(estacionesEsperadas, resultado);
    }
}
