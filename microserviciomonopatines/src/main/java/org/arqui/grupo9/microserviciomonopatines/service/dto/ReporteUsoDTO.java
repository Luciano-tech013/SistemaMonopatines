package org.arqui.grupo9.microserviciomonopatines.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReporteUsoDTO {
    private Long idMonopatin;
    private double kmsRecorridos;
    private LocalDateTime tiempoConPausa;
    private String estado;
}
