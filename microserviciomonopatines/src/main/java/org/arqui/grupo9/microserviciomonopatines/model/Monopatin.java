package org.arqui.grupo9.microserviciomonopatines.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Monopatin {

    @Id
    private Long idMonopatin;
    @Column
    private Double kmsRecorridos;
    @Column
    private Double latitud;
    @Column
    private Double longitud;
    @Setter(AccessLevel.NONE)
    @Column
    private String estado; // Representa si esta en mantenimiento o no.

    public Monopatin() {}

    public Monopatin(Long idMonopatin, double kmsRecorridos, double latitud, double longitud, String estado) {
        this.idMonopatin = idMonopatin;
        this.kmsRecorridos = kmsRecorridos;
        this.latitud = latitud;
        this.longitud = longitud;
        setEstado(estado);
    }

    public Monopatin(Long idMonopatin, double latitud, double longitud, String estado) {
        this.idMonopatin = idMonopatin;
        this.latitud = latitud;
        this.longitud = longitud;
        setEstado(estado);
    }

    public void setEstado(String estado) {
        String estadoLowCase = estado.toLowerCase();
        if(estadoLowCase.equals("mantenimiento") || estadoLowCase.equals("operacion"))
            this.estado = estadoLowCase;
    }
}
