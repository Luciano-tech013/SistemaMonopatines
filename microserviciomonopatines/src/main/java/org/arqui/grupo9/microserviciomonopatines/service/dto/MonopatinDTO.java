package org.arqui.grupo9.microserviciomonopatines.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.util.Base64;

@Data
public class MonopatinDTO implements Serializable {
    @Positive(message = "El id no puede ser negativo")
    private Long idMonopatin;

    @NotNull(message = "Los kms recorridos enviados no son validos")
    @Positive(message = "Los kms recorridos no pueden ser negativos")
    private double kmsRecorridos;

    @NotBlank(message = "Debe indicar el modelo del monopatin")
    private String modelo;

    @NotNull(message = "La latitud enviada no es valida")
    private double latitud;

    @NotNull(message = "La longitud enviada no es valida")
    private double longitud;

    @NotNull(message = "El estado enviado no es valido")
    @Pattern(regexp = "^(operacion|mantenimiento)$", message = "El estado debe ser 'operacion' o 'mantenimiento'")
    private String estado;

    @NotBlank(message = "Debe haber cargado un codigo QR valido")
    private String codigoQR;


    private boolean activo;

    public MonopatinDTO(Long idMonopatin, double kmsRecorridos, String modelo, double latitud, double longitud, String estado, String codigoQR) {
        this.idMonopatin = idMonopatin;
        this.kmsRecorridos = kmsRecorridos;
        this.modelo = modelo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.codigoQR = Base64.getEncoder().encodeToString(codigoQR.getBytes());
        this.activo = false;
    }

    //SE UTILIZA PARA QUE VIAJE PUEDA VALIDAR SI EL MONOPATIN ESTA ACTIVO
    public MonopatinDTO(Long idMonopatin, double kmsRecorridos, String modelo, double latitud, double longitud, String estado, String codigoQR, boolean activo) {
        this.idMonopatin = idMonopatin;
        this.kmsRecorridos = kmsRecorridos;
        this.modelo = modelo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.codigoQR = Base64.getEncoder().encodeToString(codigoQR.getBytes());
        this.activo = activo;
    }
}
