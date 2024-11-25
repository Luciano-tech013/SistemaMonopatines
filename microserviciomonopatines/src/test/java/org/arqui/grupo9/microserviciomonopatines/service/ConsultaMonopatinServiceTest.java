package org.arqui.grupo9.microserviciomonopatines.service;

import org.arqui.grupo9.microserviciomonopatines.model.Monopatin;
import org.arqui.grupo9.microserviciomonopatines.repository.IMonopatinRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConsultaMonopatinServiceTest {
    @Autowired
    private MonopatinService service;

    @Autowired
    private IMonopatinRepository repository;

    @Test
    @DisplayName("Test para obtener cantidad de monopatines en operacion y cantidad de monopatines en matenimiento")
    void testGetCantMonopatinesByOperacionAndMantenimiento() {
        Monopatin m1 = new Monopatin(1L, 12.00, 134.20, 92.31, "operacion");
        Monopatin m2 = new Monopatin(2L, 9.02, 77.90, 389.01, "mantenimiento");
        Monopatin m3 = new Monopatin(3L, 9.00, 205.45, 201.03, "mantenimiento");

        this.service.save(m1);
        this.service.save(m2);
        this.service.save(m3);

        int cantEnOperacion = this.service.getMonopatinesByEstado("operacion").size();
        int cantEnMantenimiento = this.service.getMonopatinesByEstado("mantenimiento").size();

        assertEquals(1, cantEnOperacion);
        assertEquals(2, cantEnMantenimiento);
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }
}
