package org.arqui.grupo9.microserviciomonopatines.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "monopatines")
public class Monopatin {
    @Id
    private Long idMonopatin;

    @Column
    private Double kmsRecorridos;

    @Column
    private String modelo;

    @Column
    private Double latitud;

    @Column
    private Double longitud;

    @Setter(AccessLevel.NONE)
    @Column
    private String estado; // Representa si esta en mantenimiento o no.

    @Column(name = "codigo_qr")
    private String codigoQR;

    public Monopatin() {}

    public Monopatin(Long idMonopatin, double kmsRecorridos, String modelo, double latitud, double longitud, String estado, String codigoQR) {
        this.idMonopatin = idMonopatin;
        this.kmsRecorridos = kmsRecorridos;
        this.modelo = modelo;
        this.latitud = latitud;
        this.longitud = longitud;
        setEstado(estado);
        this.codigoQR = codigoQR;
    }

    public Monopatin(Long idMonopatin, String modelo, double latitud, double longitud, String estado, String codigoQR) {
        this.idMonopatin = idMonopatin;
        this.modelo = modelo;
        this.latitud = latitud;
        this.longitud = longitud;
        setEstado(estado);
        this.codigoQR = codigoQR;
    }

    public void setEstado(String estado) {
        String estadoLowCase = estado.toLowerCase();
        if(estadoLowCase.equals("mantenimiento") || estadoLowCase.equals("operacion"))
            this.estado = estadoLowCase;
    }
}
