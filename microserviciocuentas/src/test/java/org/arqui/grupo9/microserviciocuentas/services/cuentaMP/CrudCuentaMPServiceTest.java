package org.arqui.grupo9.microserviciocuentas.services.cuentaMP;

import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.ICuentaMpRepository;
import org.arqui.grupo9.microserviciocuentas.services.CuentaMpService;
import org.arqui.grupo9.microserviciocuentas.services.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CrudCuentaMPServiceTest {
    @Autowired
    private CuentaMpService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ICuentaMpRepository repository;

    @Test
    @DisplayName("Test para guardar una cuenta de mercado pago en el sistema")
    void testSaveCuentaMP() {
        CuentaMP cuenta = new CuentaMP(1L, 200.00);

        assertTrue(this.service.saveCuenta(cuenta));
    }

    @Test
    @DisplayName("Test para buscar una cuenta de mercado pago por su ID")
    void testFindByIdCuenta() {
        CuentaMP cuenta = new CuentaMP(1L, 200.00);

        this.service.saveCuenta(cuenta);

        this.service.findByIdCuenta(1L);
    }

    @Test
    @DisplayName("Test para obtener todas las cuentas cargadas en el sistema")
    void testFindAllCuentas() {
        CuentaMP cuenta = new CuentaMP(1L, 205.20);
        CuentaMP cuenta2 = new CuentaMP(2L, 346.00);

        this.service.saveCuenta(cuenta);
        this.service.saveCuenta(cuenta2);

        List<CuentaMP> cuentas = this.service.findAllCuentas();
        assertEquals(2, cuentas.size());
    }

    @Test
    @DisplayName("Test para crear una cuenta y cargarla en el sistema")
    void testCargarCuenta() {
        Usuario u = new Usuario(1L, "Luciano", "Torres", "tluciano943@gmail.com", "2494014611", "1234");

        this.usuarioService.save(u);
        this.service.crearCuenta(u.getIdUsuario());

        assertTrue(this.service.findByIdCuenta(1L).tieneUsuario(u));
        assertEquals(1L, this.service.findByIdCuenta(1L).getIdCuentaMP());
    }

    @Test
    @DisplayName("Test para cargar saldo en una cuenta")
    void testCargarSaldoEnCuenta() {
        CuentaMP cuenta = new CuentaMP(1L, 0.00);

        this.service.saveCuenta(cuenta);
        this.service.cargarSaldoCuenta(1L, 510.00);

        assertEquals(740, this.service.findByIdCuenta(1L).getSaldo());
    }

    @Test
    @DisplayName("Test para desscontar saldo en una cuenta")
    void testDescontarSaldoEnCuenta() {
        CuentaMP cuenta = new CuentaMP(2L, 0.00);

        this.service.saveCuenta(cuenta);
        this.service.cargarSaldoCuenta(2L, 234.00);
        this.service.descontarSaldoCuenta(2L, 100.00);

        assertEquals(134.00, this.service.findByIdCuenta(2L).getSaldo());
    }

    @Test
    @DisplayName("Test para inhabilitar cuenta")
    void testInhabilitarCuenta() {
        CuentaMP cuenta = new CuentaMP(3L, 0.00);

        this.service.saveCuenta(cuenta);
        this.service.inhabilitarCuenta(3L, LocalDate.of(2024, 11, 11));

        CuentaMP cuentaInhabilitada = this.service.findByIdCuenta(3L);
        assertTrue(cuentaInhabilitada.isInhabilitada());
        assertEquals(LocalDate.of(2024, 11, 11), cuentaInhabilitada.getFechaInahilitada());
    }

    @Test
    @DisplayName("Test para saber si una cuenta ya esta habilitada nuevamente o no")
    void testEstaHabilitadaCuenta() {
        CuentaMP cuenta = new CuentaMP(4L, 127.30);

        this.service.saveCuenta(cuenta);
        this.service.inhabilitarCuenta(3L, LocalDate.of(2024, 11, 8));

        assertTrue(this.service.estaHabilitada(3L));
    }

    /*@AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }*/
}
