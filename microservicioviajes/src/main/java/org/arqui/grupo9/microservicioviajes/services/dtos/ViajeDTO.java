package org.arqui.grupo9.microservicioviajes.services.dtos;

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
    private long tiempoTotal;

    public ViajeDTO(LocalDateTime fechaIniViaje, LocalDateTime fechaFinViaje, Double kmsRecorridos, Double costoTotal) {
        this.fechaIniViaje = fechaIniViaje;
        this.fechaFinViaje = fechaFinViaje;
        this.kmsRecorridos = kmsRecorridos;
        this.costoTotal = costoTotal;
        setTiempoTotal();
    }

    public void setTiempoTotal() {
        if(this.fechaIniViaje != null && this.fechaFinViaje != null)
            this.tiempoTotal = Duration.between(this.fechaIniViaje, this.fechaFinViaje).toMinutes();
    }

    //¿Deberia retornar el usuario y el monopatin o no?
}
