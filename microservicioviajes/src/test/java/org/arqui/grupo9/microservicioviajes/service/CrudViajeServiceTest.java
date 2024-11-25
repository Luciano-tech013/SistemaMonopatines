package org.arqui.grupo9.microservicioviajes.service;

import org.arqui.grupo9.microservicioviajes.model.Viaje;
import org.arqui.grupo9.microservicioviajes.repository.IViajeRepository;
import org.arqui.grupo9.microservicioviajes.services.ViajeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CrudViajeServiceTest {
    @Autowired
    private ViajeService service;

    private IViajeRepository repository;

    @Test
    @DisplayName("Test para obtencion de un viaje por su identificador")
    void testFindById() {
        Long id = 1L;

        Viaje viaje = this.service.findById(id);

        assertEquals(id, viaje.getIdViaje());
    }
}
