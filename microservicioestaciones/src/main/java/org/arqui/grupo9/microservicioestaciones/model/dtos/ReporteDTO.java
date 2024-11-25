package org.arqui.grupo9.microservicioestaciones.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReporteDTO {
    private EstacionDTO estacion;
    private List<MonopatinDTO> monopatin;
}
