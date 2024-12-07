package org.arqui.grupo9.microservicioviajes.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ReporteTiempoTotalPausadoDTO implements Serializable {
    private Long horas;
    private Long minutos;
}
