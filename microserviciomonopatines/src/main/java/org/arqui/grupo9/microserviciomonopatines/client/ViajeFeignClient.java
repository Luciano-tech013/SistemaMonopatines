package org.arqui.grupo9.microserviciomonopatines.client;

import org.arqui.grupo9.microserviciomonopatines.service.dto.ReporteTiempoTotalPausadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.Duration;

@FeignClient(name = "microservicioviajes")
public interface ViajeFeignClient {

    @GetMapping("/api/viajes/monopatin/{idMonopatin}/reporte/tiempopausado")
    ResponseEntity<ReporteTiempoTotalPausadoDTO> getTiempoTotalPausadoDeMonopatin(@PathVariable Long idMonopatin);
}
