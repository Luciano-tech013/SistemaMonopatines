package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Debe haber un QR cargado en el monopatin")
    private String codigoQR;

    private boolean activo;

    public MonopatinDTO(Long idMonopatin, double kmsRecorridos, double latitud, double longitud, String estado, String codigoQR, boolean activo) {
        this.idMonopatin = idMonopatin;
        this.kmsRecorridos = kmsRecorridos;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.codigoQR = codigoQR;
        this.activo = activo;
    }
}
