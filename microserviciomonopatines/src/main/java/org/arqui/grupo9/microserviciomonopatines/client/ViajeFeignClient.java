package org.arqui.grupo9.microserviciomonopatines.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@FeignClient(name = "microservicioviaje")
public interface ViajeFeignClient {

    @GetMapping("/monopatin/reporte/tiempopausado/{idMonopatin}")
    ResponseEntity<LocalDateTime> getTiempoTotalPausadoDeMonopatin(@PathVariable Long idMonopatin);
}
