package org.arqui.grupo9.microservicioviajes.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Viaje {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long idViaje;

    @Column(name = "fecha_ini_viaje")
    private LocalDateTime fechaIniViaje;

    @Column(name = "fecha_fin_viaje")
    private LocalDateTime fechaFinViaje;

    @Column(name = "kms_recorridos")
    private Double kmsRecorridos;

    @Column(name = "costo_total")
    private Double costoTotal;

    @Column(name = "fecha_ini_viaje_con_pausa")
    private LocalDateTime fechaIniViajeConPausa;

    @Column(name = "fecha_fin_viaje_con_pausa")
    private LocalDateTime fechaFinViajeConPausa;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_monopatin")
    private Long idMonopatin;

    @Transient
    public static double PRECIO_BASE = 10.00;

    @Transient
    public static final double PRECIO_RECARGO = 5.00;

    @Transient
    private static final double TIEMPO_PAUSA_PERMITIDO = 15;

    /* Aplico recargo se puede calcular*/

    public Viaje() {}

    public Viaje(LocalDateTime fechaIniViaje, Long idUsuario, Long idMonopatin) {
        this.fechaIniViaje = fechaIniViaje;
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
    }

    public void setPrecio(double precio) {
        PRECIO_BASE = precio;
    }
}
