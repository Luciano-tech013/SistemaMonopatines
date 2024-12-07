package org.arqui.grupo9.microserviciomonopatines.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ReporteTiempoTotalPausadoDTO implements Serializable {
    private Long horas;
    private Long minutos;
}
