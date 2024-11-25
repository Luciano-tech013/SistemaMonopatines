package org.arqui.grupo9.microserviciocuentas.controllers;

import jakarta.validation.constraints.NotEmpty;
import org.arqui.grupo9.microserviciocuentas.services.CuentaSistemaService;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaRequestDTO;
import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaResponseDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.CuentaSistemaConverterResponse;
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
    private CuentaSistemaConverterResponse converterResponse;

    public CuentaSistemaController(CuentaSistemaService cuentaSistemaService, @Lazy CuentaSistemaConverterResponse converterResponse) {
        this.cuentaSistemaService = cuentaSistemaService;
        this.converterResponse = converterResponse;
    }

    @GetMapping
    public ResponseEntity<List<CuentaSistemaResponseDTO>> findAll() {
        List<CuentaSistema> cuentasSistema = this.cuentaSistemaService.findAll();
        if (cuentasSistema.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converterResponse.fromEntity(cuentasSistema), HttpStatus.OK);
    }

    @GetMapping("/{idCuentaSistema}")
    public ResponseEntity<CuentaSistemaResponseDTO> findById(@PathVariable Long idCuentaSistema) {
        CuentaSistema cuenta = this.cuentaSistemaService.findById(idCuentaSistema);
        return new ResponseEntity<>(this.converterResponse.fromEntity(cuenta), HttpStatus.FOUND);
    }

    @PostMapping("/crear")
    public ResponseEntity<Boolean> crearCuenta(@RequestBody CuentaSistemaRequestDTO cuentaSistemaDTO) {
        return new ResponseEntity<>(this.cuentaSistemaService.crearCuenta(cuentaSistemaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{idUsuario}/cuenta/{idCuentaSistema}/asignar")
    public ResponseEntity<Boolean> asignarNuevoUsuario(@PathVariable Long idUsuario, @PathVariable Long idCuentaSistema) {
        return new ResponseEntity<>(this.cuentaSistemaService.asignarNuevoUsuario(idUsuario, idCuentaSistema), HttpStatus.OK);
    }

    @PutMapping("/usuario/{idUsuario}/cuenta/{idCuentaSistema}/desvincular")
    public ResponseEntity<Boolean> desvincularUsuario(@PathVariable Long idUsuario, @PathVariable Long idCuentaSistema) {
        return new ResponseEntity<>(this.cuentaSistemaService.desvincularUsuario(idUsuario, idCuentaSistema), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaSistema}/mercadopago/{idCuentaMp}/desvincular")
    public ResponseEntity<Boolean> desvincularCuentaMercadoPago(@PathVariable Long idCuentaSistema, @PathVariable Long idCuentaMp) {
        return new ResponseEntity<>(this.cuentaSistemaService.desvincularCuentaMercadoPago(idCuentaSistema, idCuentaMp), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaSistema}/mercadopago/{idCuentaMp}/vincular")
    public ResponseEntity<Boolean> vincularNuevaCuentaMercadoPago(@PathVariable Long idCuentaSistema, @PathVariable Long idCuentaMp) {
        return new ResponseEntity<>(this.cuentaSistemaService.vincularNuevaCuentaMercadoPago(idCuentaSistema, idCuentaMp), HttpStatus.OK);
    }

    @DeleteMapping("/{idCuentaSistema}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idCuentaSistema) {
        return new ResponseEntity<>(this.cuentaSistemaService.deleteById(idCuentaSistema), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaSistema}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long idCuentaSistema, @RequestBody CuentaSistemaRequestDTO cuentaSistemaDTO) {
        return new ResponseEntity<>(this.cuentaSistemaService.updateById(idCuentaSistema, cuentaSistemaDTO), HttpStatus.OK);
    }

    //PUEDE TRAER PROBLEMAS DE MAPEO PORQUE YA EXISTE OTRO ENDPOINT IGUAL
    @PutMapping("/{idCuentaSistema}/inhabilitar")
    public ResponseEntity<Boolean> inhabilitarCuenta(@PathVariable Long idCuentaSistema,
                                                     @RequestParam(name = "hasta", required = true) @NotEmpty(message = "La fecha no puede ser vacia") LocalDate fechaHasta) {
        return new ResponseEntity<>(this.cuentaSistemaService.inhabilitarCuenta(idCuentaSistema, fechaHasta), HttpStatus.OK);
    }
}
