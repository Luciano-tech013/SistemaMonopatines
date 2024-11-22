package org.arqui.grupo9.microservicioviajes.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonopatinDTO {
    private int idMonopatin;
    private double kmsRecorridos;
}
