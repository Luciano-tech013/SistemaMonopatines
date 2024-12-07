package org.arqui.grupo9.microserviciomonopatines.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReporteUsoDTO {
    private Long idMonopatin;
    private double kmsRecorridos;
    private Long horas;
    private Long minutos;
    private String estado;
}
