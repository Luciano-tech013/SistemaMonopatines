package org.arqui.grupo9.microservicioviajes.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaMpDTO {
    private int dni;
    private double credito;
}
