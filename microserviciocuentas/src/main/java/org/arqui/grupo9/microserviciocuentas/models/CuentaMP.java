package org.arqui.grupo9.microserviciocuentas.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "cuentas_mp")
public class CuentaMP {
    @Id
    private Long idCuentaMP;

    @Column(nullable = false)
    private Double saldo;

    @Column(name = "fecha_inhabilitada")
    private LocalDate fechaInahilitada;

    @Column
    private boolean inhabilitada;

    @ManyToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "cuentas_sistemas",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_cuentaMp")
    )
    private Set<Usuario> usuarios;

    public CuentaMP() {}

    public CuentaMP(Long idCuentaMP, Double saldo, HashSet<Usuario> usuarios) {
        this.idCuentaMP = idCuentaMP;
        this.saldo = saldo;
        this.usuarios = usuarios;
    }

    public CuentaMP(Long idCuentaMP, Double saldo) {
        this.idCuentaMP = idCuentaMP;
        this.saldo = saldo;
        this.usuarios = new HashSet<>();
    }

    public CuentaMP(Double saldo) {
        this.saldo = saldo;
        this.usuarios = new HashSet<>();
    }

    public boolean tieneUsuario(Usuario u) {
        return this.usuarios.contains(u);
    }

    public void registrarUsuario(Usuario u) {
        this.usuarios.add(u);
    }

    public void eliminarUsuario(Usuario u) {
        this.usuarios.remove(u);
    }

    @Override
    public boolean equals(Object o) {
        CuentaMP otraCuenta = (CuentaMP) o;
        return this.idCuentaMP.equals(otraCuenta.getIdCuentaMP());
    }
}
