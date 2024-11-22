package org.arqui.grupo9.microservicioviajes.model.dtos;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter @Setter
public class ViajeDTO {

    private LocalDateTime fechaIniViaje;

    private LocalDateTime fechaFinViaje;

    private Double kmsRecorridos;

    private Double costoTotal;

    @Setter(AccessLevel.NONE)
    private Duration tiempoTotal;

    public ViajeDTO(LocalDateTime fechaIniViaje, LocalDateTime fechaFinViaje, Double kmsRecorridos, Double costoTotal) {
        this.fechaIniViaje = fechaIniViaje;
        this.fechaFinViaje = fechaFinViaje;
        this.kmsRecorridos = kmsRecorridos;
        this.costoTotal = costoTotal;
        setTiempoTotal();
    }

    public void setTiempoTotal() {
        this.tiempoTotal = Duration.between(this.fechaIniViaje, this.fechaFinViaje);
    }

    //¿Deberia retornar el usuario y el monopatin o no?
}
