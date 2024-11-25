package org.arqui.grupo9.microserviciomonopatines.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteEstadoDTO {

    private Integer monopatinEnMantenimiento;
    private Integer monopatinEnServicio;


}
