package org.arqui.grupo9.microserviciocuentas.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.arqui.grupo9.microserviciocuentas.services.CuentaSistemaService;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaDTO;
import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaWithRolesDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.ViajeDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.CuentaSistemaConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaSistemaController {
    private CuentaSistemaService cuentaSistemaService;
    private CuentaSistemaConverter converterResponse;

    public CuentaSistemaController(CuentaSistemaService cuentaSistemaService, @Lazy CuentaSistemaConverter converterResponse) {
        this.cuentaSistemaService = cuentaSistemaService;
        this.converterResponse = converterResponse;
    }

    @GetMapping
    public ResponseEntity<List<CuentaSistemaDTO>> findAll() {
        List<CuentaSistema> cuentasSistema = this.cuentaSistemaService.findAll();
        if (cuentasSistema.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converterResponse.fromEntity(cuentasSistema), HttpStatus.OK);
    }

    @GetMapping("/{idCuentaSistema}")
    public ResponseEntity<CuentaSistemaDTO> findById(@PathVariable @Positive(message = "Un ID no puede ser negativo") Long idCuentaSistema) {
        CuentaSistema cuenta = this.cuentaSistemaService.findById(idCuentaSistema);
        return new ResponseEntity<>(this.converterResponse.fromEntity(cuenta), HttpStatus.FOUND);
    }

    @PostMapping("/crear")
    public ResponseEntity<Boolean> crearCuenta(@RequestBody @NotNull(message = "Debe enviar los datos para crear la entidad")@Valid CuentaSistemaDTO cuentaSistemaDTO) {
        return new ResponseEntity<>(this.cuentaSistemaService.crearCuenta(cuentaSistemaDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCuentaSistema}")
    public ResponseEntity<Boolean> eliminarCuenta(@PathVariable @Positive(message = "Un ID no puede ser negativo") Long idCuentaSistema) {
        return new ResponseEntity<>(this.cuentaSistemaService.eliminarCuenta(idCuentaSistema), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaSistema}")
    public ResponseEntity<Boolean> updateById(
            @PathVariable @Positive(message = "Un ID no puede ser negativo") Long idCuentaSistema,
            @RequestBody @NotNull(message = "Debe enviar los datos para actualizar") @Valid CuentaSistemaDTO cuentaSistemaDTO) {
        return new ResponseEntity<>(this.cuentaSistemaService.updateById(idCuentaSistema, cuentaSistemaDTO), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaSistema}/inhabilitar")
    public ResponseEntity<Boolean> inhabilitarCuenta(
            @PathVariable @Positive(message = "Un ID no puede ser negativo") Long idCuentaSistema,
            @RequestParam(name = "hasta", required = true) @NotEmpty(message = "La fecha no puede ser vacia") LocalDate fechaHasta) {
        return new ResponseEntity<>(this.cuentaSistemaService.inhabilitarCuenta(idCuentaSistema, fechaHasta), HttpStatus.OK);
    }
}
