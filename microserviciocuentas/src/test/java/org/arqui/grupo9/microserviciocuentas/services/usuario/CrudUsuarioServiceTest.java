package org.arqui.grupo9.microserviciocuentas.services.usuario;

import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.IUsuarioRepository;
import org.arqui.grupo9.microserviciocuentas.services.CuentaMpService;
import org.arqui.grupo9.microserviciocuentas.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CrudUsuarioServiceTest {
    @Autowired
    private UsuarioService service;

    @Autowired
    private CuentaMpService cuentaService;

    @Autowired
    private IUsuarioRepository repository;

    private Usuario u1;
    private Usuario u2;
    private CuentaMP c1;

    @BeforeEach
    void setUp() {
        this.u1 = new Usuario(1L, "Luciano", "Torres", "2494014611", "tluciano943@gmail.com", "1234");
        this.u2 = new Usuario(2L, "Adrian", "Torres", "2494658798", "tluciano943@gmail.com", "1234");
        this.c1 = new CuentaMP(1L, 200.00);

        this.service.save(u1);
        this.service.save(u2);
        this.cuentaService.saveCuenta(c1);
    }

    @Test
    @DisplayName("Test para guardar un usuario en el sistema")
    void testSaveUsuario() {
        Usuario u = new Usuario(1L, "Luciano", "Torres", "2494014611", "tluciano943@gmail.com", "1234");

        assertTrue(this.service.save(u));
    }

    @Test
    @DisplayName("Test para buscar un usuario por su identificador")
    void testFindByIdUsuario() {
        Long id = 1L;

        this.service.save(new Usuario(1L, "Luciano", "Torres", "2494014611", "tluciano943@gmail.com", "1234"));
        Usuario usuario = this.service.findById(id);

        assertEquals(id, usuario.getIdUsuario());
    }

    @Test
    @DisplayName("Test para buscar todos los usuarios cargados en el sistema")
    void testFindAllUsuarios() {
        Usuario u1 = new Usuario(1L, "Luciano", "Torres", "2494014611", "tluciano943@gmail.com", "1234");
        Usuario u2 = new Usuario(2L, "Adrian", "Torres", "2494658468", "adriant@gmail.com", "1234");

        this.service.save(u1);
        this.service.save(u2);

        List<Usuario> usuarios = this.service.findAll();
        assertEquals(2, usuarios.size());
    }

    @Test
    @DisplayName("Test para eliminar un usuario por su identificador")
    void testDeleteByIdUsuario() {
        Long id = 1L;

        this.service.save(new Usuario(id, "Luciano", "Torres", "2494014611", "tluciano943@gmail.com", "1234"));
        this.service.deleteById(id);

        this.service.findById(id);
    }

    @Test
    @DisplayName("Test para actualizar un usuario por su identificador")
    void testUpdateByIdUsuario() {
        Long id = 1L;
        Usuario uModified = new Usuario(id, "Luciano", "Torres", "2494014611", "tluciano943@gmail.com", "1234");

        this.service.save(new Usuario(id, "Alejandro", "Lopez", "2494014611", "tluciano943@gmail.com", "1234"));
        this.service.updateById(id, uModified);
        Usuario uFinded = this.service.findById(id);

        assertEquals(uModified.getNombre(), uFinded.getNombre());
        assertEquals(uModified.getApellido(), uFinded.getApellido());
        assertEquals(uModified.getNroCelular(), uFinded.getNroCelular());
        assertEquals(uModified.getEmail(), uFinded.getEmail());
    }

    @Test
    @DisplayName("Test para asociar una cuenta a un usuario")
    void testAsociarCuenta() {
        assertTrue(this.service.asociarCuenta(this.u1.getIdUsuario(), this.c1.getIdCuentaMP()));
        assertTrue(this.service.findById(1L).tieneCuenta(this.c1));
        assertEquals(1, this.service.findById(1L).getCuentasMp().size());
        assertEquals(1, this.cuentaService.findByIdCuenta(1L).getUsuarios().size());
    }

    @Test
    @DisplayName("Test para eliminar una cuenta asociada a un usuario")
    void testEliminarCuentaAsociadaAUsuario() {
        this.service.asociarCuenta(this.u1.getIdUsuario(), this.c1.getIdCuentaMP());
        this.service.asociarCuenta(this.u2.getIdUsuario(), this.c1.getIdCuentaMP());

        assertTrue(this.service.eliminarCuentaDeUsuario(this.u1.getIdUsuario(), this.c1.getIdCuentaMP()));
        assertFalse(this.service.findById(1L).tieneCuenta(this.c1));
        assertFalse(this.cuentaService.findByIdCuenta(1L).tieneUsuario(this.u1));
        assertEquals(1, this.cuentaService.findByIdCuenta(1L).getUsuarios().size());
        assertEquals(0, this.service.findById(1L).getCuentasMp().size());
    }

    /*@AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }*/
}
