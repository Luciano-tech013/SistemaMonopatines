package org.arqui.grupo9.microservicioviajes.service;

import org.arqui.grupo9.microservicioviajes.model.Viaje;
import org.arqui.grupo9.microservicioviajes.repository.IViajeRepository;
import org.arqui.grupo9.microservicioviajes.services.ViajeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RealizarViajeServiceTest {
    @Autowired
    private ViajeService service;

    private IViajeRepository repository;

    @Test
    @DisplayName("Test para generar y finalizar un viaje, y comprobar que se estan contabilizando los datos necesarios")
    void testGenerarYFinalizarViaje() throws InterruptedException {
        double credito = 150.00;
        int kms = 15;

        this.service.generar(1105L, 3L);
        Thread.sleep(15000);
        this.service.finalizar();
        Viaje viaje = this.service.findById(1L);

        assertFalse(this.service.isViajeEnCurso().get());
        assertEquals(credito, this.service.getCreditoDescontado());
        assertEquals(kms, this.service.getKmsRecorridos());
        assertEquals(1L, viaje.getIdViaje());
    }

    @Test
    @DisplayName("Test para pausar un viaje y comprobar que se este contabilizando unicamente el credito durante la duracion de la pausa")
    void testGenerarPausarYFinalizarViaje() throws InterruptedException {
        double credito = 250.00;
        int kms = 15;

        /*Genero el viaje*/
        this.service.generar(905L, 5L);
        Thread.sleep(15000);
        /*Luego de 15 segundos, pauso el viaje para contabilizar solo el credito durante 10 segundos*/
        this.service.pausar();
        Thread.sleep(10000);
        this.service.finalizar();
        Viaje viaje = this.service.findById(2L);

        assertFalse(this.service.isViajeEnCurso().get());
        assertEquals(credito, this.service.getCreditoDescontado());
        assertEquals(kms, this.service.getKmsRecorridos());
        assertEquals(2L, viaje.getIdViaje());
    }

    @Test
    @DisplayName("Test para despausar un viaje y comprobar que se esten contabilizando el credito y los kms una vez finalizada la pausa")
    void testDespausarViaje() throws InterruptedException {
        double credito = 470.00;
        double kms = 35;

        this.service.generar(563L, 2L);
        Thread.sleep(20000); //200 //20
        this.service.pausar();
        Thread.sleep(12000); //320
        this.service.despausar();
        Thread.sleep(15000); //470 //35
        this.service.finalizar();
        Viaje viaje = this.service.findById(3L);

        assertFalse(this.service.isViajeEnCurso().get());
        assertEquals(credito, this.service.getCreditoDescontado());
        assertEquals(kms, this.service.getKmsRecorridos());
        assertEquals(3L, viaje.getIdViaje());
    }

    @Test
    @DisplayName("Test para aplicar recargo sobre pausa de viaje mayor a 15 minutos")
    void testAplicarRecargoSobrePausa() throws InterruptedException {
        double credito = 580.00;
        double kms = 40;

        this.service.generar(290L, 7L);
        Thread.sleep(30000);
        this.service.pausar(); //300 30
        Thread.sleep(17000);
        this.service.despausar(); //480
        Thread.sleep(10000);
        this.service.finalizar(); //580 en total 40
        Viaje viaje = this.service.findById(4L);

        assertFalse(this.service.isViajeEnCurso().get());
        assertEquals(credito, this.service.getCreditoDescontado());
        assertEquals(kms, this.service.getKmsRecorridos());
        assertEquals(4L, viaje.getIdViaje());
    }
}
