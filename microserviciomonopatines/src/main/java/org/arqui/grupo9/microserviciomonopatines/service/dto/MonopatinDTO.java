package org.arqui.grupo9.microserviciomonopatines.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;

@Data
public class MonopatinDTO implements Serializable {
    @Positive(message = "El id no puede ser negativo")
    private Long idMonopatin;

    @NotNull(message = "Los kms recorridos enviados no son validos")
    @Positive(message = "Los kms recorridos no pueden ser negativos")
    private double kmsRecorridos;

    @NotNull(message = "La latitud enviada no es valida")
    private double latitud;

    @NotNull(message = "La longitud enviada no es valida")
    private double longitud;

    @NotNull(message = "El estado enviado no es valido")
    @Pattern(regexp = "^(operacion|mantenimiento)$", message = "El estado debe ser 'operacion' o 'mantenimiento'")
    private String estado;

    public MonopatinDTO(Long idMonopatin, double kmsRecorridos, double latitud, double longitud, String estado) {
        this.idMonopatin = idMonopatin;
        this.kmsRecorridos = kmsRecorridos;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
    }
}
