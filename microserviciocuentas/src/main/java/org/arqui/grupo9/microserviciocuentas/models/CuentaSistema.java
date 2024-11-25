package org.arqui.grupo9.microserviciocuentas.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "cuentasSistema")
    private Set<Usuario> usuarios;

    @ManyToOne(targetEntity = CuentaMP.class, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private CuentaMP cuentaMp;

    public CuentaSistema() {}

    public CuentaSistema(String username, String password, CuentaMP cuentaMp) {
        this.username = username;
        this.password = password;
        this.fechaInahilitada = null;
        this.inhabilitada = false;
        this.usuarios = new HashSet<>();
        this.cuentaMp = cuentaMp;
    }

    public boolean tieneUsuario(Usuario u) {
        return this.usuarios.contains(u);
    }

    public boolean tieneUsuarios() {
        return this.usuarios.isEmpty();
    }

    public void asignarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void desasignarUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
    }

    public boolean tieneCuentaMercadoPago(CuentaMP otraCuenta) {
        return this.cuentaMp.equals(otraCuenta);
    }

    public void asignarNuevaCuentaMercadoPago(CuentaMP cuenta) {
        this.cuentaMp = cuenta;
    }

    public void desvincularCuentaMercadoPago() {
        this.cuentaMp = null;
    }
}
