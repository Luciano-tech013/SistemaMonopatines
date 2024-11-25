package org.arqui.grupo9.microservicioestaciones.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonopatinDTO {
    private Long idMonopatin;
    private Double kmsRecorridos;
    private Double latitud;
    private Double longitud;
    private String estado;
}
