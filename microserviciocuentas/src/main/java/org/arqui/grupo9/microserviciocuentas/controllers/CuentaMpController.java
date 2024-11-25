package org.arqui.grupo9.microserviciocuentas.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.services.CuentaMpService;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaMpDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.CuentaMPConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mercadopago/cuentas")
public class CuentaMpController {
    private CuentaMpService cuentaMpService;
    private CuentaMPConverter converter;

    public CuentaMpController(CuentaMpService cuentaMpService, @Lazy CuentaMPConverter converter) {
        this.cuentaMpService = cuentaMpService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<CuentaMpDTO>> findAll() {
        List<CuentaMP> cuentasMp = this.cuentaMpService.findAllCuentas();
        if (cuentasMp.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converter.fromEntity(cuentasMp), HttpStatus.OK);
    }

    @GetMapping("/{idCuentaMP}")
    public ResponseEntity<CuentaMpDTO> findById(@PathVariable Long idCuentaMP) {
        CuentaMP cuenta = this.cuentaMpService.findById(idCuentaMP);
        return new ResponseEntity<>(this.converter.fromEntity(cuenta), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody @Valid CuentaMpDTO cuenta) {
        return new ResponseEntity<>(this.cuentaMpService.save(this.converter.fromDTO(cuenta)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCuentaMP}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idCuentaMP) {
        return new ResponseEntity<>(this.cuentaMpService.deleteById(idCuentaMP), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaMP}/cargarSaldo")
    public ResponseEntity<Boolean> cargarSaldo(@PathVariable Long idCuentaMP,
                                               @RequestParam(name="saldo", required = true) @NotEmpty(message="El sado no puede ser vacio") @Positive(message="El saldo no puede ser negativo") double saldo) {
        return new ResponseEntity<>(this.cuentaMpService.cargarSaldo(idCuentaMP, saldo), HttpStatus.OK);
    }

    @PutMapping("/{idCuentaMP}/descontarSaldo/viaje")
    public ResponseEntity<Boolean> descontarSaldo(@PathVariable Long idCuentaMP,
                                                  @RequestParam(name="saldo", required = true) @NotEmpty(message="El sado no puede ser vacio") @Positive(message="El saldo no puede ser negativo") double saldo) {
        return new ResponseEntity<>(this.cuentaMpService.descontarSaldo(idCuentaMP, saldo), HttpStatus.OK);
    }
}
