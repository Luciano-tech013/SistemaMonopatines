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

    @GetMapping("/api/cuentas/{idCuentaSistema}/mercadopago/cuenta")
    ResponseEntity<CuentaMpDTO> getCuentaMPOfCuenta(@PathVariable Long idCuentaSistema);

    @GetMapping("/api/cuentas/{idCuentaSistema}")
    ResponseEntity<CuentaSistemaDTO> findById(@PathVariable Long idCuentaSistema);
}
