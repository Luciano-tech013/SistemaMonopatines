package org.arqui.grupo9.microservicioviajes.clients;

import jakarta.validation.Valid;
import org.arqui.grupo9.microservicioviajes.services.dtos.CuentaMpDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microserviciocuentas")
public interface CuentaFeignClient {

    @GetMapping("/api/mercadopago/cuentas/{idUsuario}")
    ResponseEntity<CuentaMpDTO> findById(@PathVariable Long idUsuario);

    @PostMapping("/api/mercadopago/cuentas")
    ResponseEntity<Boolean> save(@RequestBody @Valid CuentaMpDTO cuenta);
}
