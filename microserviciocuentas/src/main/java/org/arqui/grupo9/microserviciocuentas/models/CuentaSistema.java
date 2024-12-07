package org.arqui.grupo9.microserviciocuentas.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Entity
@Getter @Setter
@EqualsAndHashCode
@Table(name = "cuentas_sistema")
public class CuentaSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuentaSistema;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_inhabilitada")
    private LocalDate fechaInahilitada;

    @Column
    private boolean inhabilitada;

    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

    @Column(name = "id_cuenta_mp")
    private Long idCuentaMP;

    public CuentaSistema() {}

    public CuentaSistema(String username, String password, Usuario usuario, Long idCuentaMP) {
        this.username = username;
        this.password = password;
        this.fechaInahilitada = null;
        this.inhabilitada = false;
        this.usuario = usuario;
        this.idCuentaMP = idCuentaMP;
    }
}
