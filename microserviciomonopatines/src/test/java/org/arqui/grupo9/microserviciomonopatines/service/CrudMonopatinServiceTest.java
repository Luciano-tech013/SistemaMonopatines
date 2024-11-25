package org.arqui.grupo9.microserviciomonopatines.service;

import org.arqui.grupo9.microserviciomonopatines.MicroserviciomonopatinesApplication;
import org.arqui.grupo9.microserviciomonopatines.model.Monopatin;
import org.arqui.grupo9.microserviciomonopatines.repository.IMonopatinRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MicroserviciomonopatinesApplication.class)
public class CrudMonopatinServiceTest {
    @Autowired
    private MonopatinService service;

    @Autowired
    private IMonopatinRepository repository;

    @Test
    @DisplayName("Test para probar de guardar un monopatin")
    void testSaveMonopatin() {
        Monopatin monopatin = new Monopatin(1L, 10.00, 323.90, 256.07, "operacion");

        assertTrue(this.service.save(monopatin));
    }

    @Test
    @DisplayName("Test para obtener un monopatin por su identificador")
    void testFindByIdMonopatin() {
        Long id = 1L;

        this.service.save(new Monopatin(1L, 10.00, 323.90, 256.07, "operacion"));
        Monopatin monopatin = this.service.findById(id);

        assertEquals(id, monopatin.getIdMonopatin());
    }

    @Test
    @DisplayName("Test para obtener todos los monopatines cargados en el sistema")
    void testFindAllMonopatines() {
        Monopatin monopatin = new Monopatin(1L, 7.05, 178.90, 231.02, "mantenimiento");
        Monopatin monopatin2 = new Monopatin(2L, 3.23, 203.40, 219.21, "operacion");

        this.service.save(monopatin);
        this.service.save(monopatin2);

        List<Monopatin> monopatines = this.service.findAll();
        assertEquals(2, monopatines.size());
    }

    @Test
    @DisplayName("Test para eliminar un monopatin por su identificador")
    void testDeleteByIdMonopatin() {
        Long id = 1L;

        this.service.save(new Monopatin(id, 10.00, 323.90, 256.07, "operacion"));
        this.service.deleteByid(id);

        this.service.findById(id);
    }

    @Test
    @DisplayName("Test para actualizar un monopatin por su identificador")
    void testUpdateByIdMonopatin() {
        Long id = 1L;
        Monopatin mModified = new Monopatin(id, 2.21, 121.01, 98.70, "mantenimiento");

        this.service.save(new Monopatin(id, 10.00, 323.90, 256.07, "operacion"));
        this.service.updateById(id, mModified);
        Monopatin mFinded = this.service.findById(id);

        assertEquals(mModified.getKmsRecorridos(), mFinded.getKmsRecorridos());
        assertEquals(mModified.getLatitud(), mFinded.getLatitud());
        assertEquals(mModified.getLongitud(), mFinded.getLongitud());
        assertEquals(mModified.getEstado(), mFinded.getEstado());
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }
}
