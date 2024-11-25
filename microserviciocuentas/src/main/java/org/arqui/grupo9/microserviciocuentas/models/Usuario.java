package org.arqui.grupo9.microserviciocuentas.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String nroCelular;

    @Column(nullable = false)
    private String email;

    @ManyToMany(targetEntity = CuentaSistema.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_vinculados",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_cuenta_sistema")
    )
    private Set<CuentaSistema> cuentasSistema;

    @ManyToMany(targetEntity = Roles.class, fetch = FetchType.LAZY)
    private Set<Roles> roles;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String nroCelular, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCelular = nroCelular;
        this.email = email;
        this.cuentasSistema = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public boolean tieneCuenta(CuentaSistema cuenta) {
        return this.cuentasSistema.contains(cuenta);
    }

    public void asociarCuenta(CuentaSistema cuenta) {
        this.cuentasSistema.add(cuenta);
    }

    public void eliminarCuenta(CuentaSistema cuenta) {
        this.cuentasSistema.remove(cuenta);
    }

    public boolean tieneCuentasSistema() {
        return !this.cuentasSistema.isEmpty();
    }

    public boolean tieneRol(Roles rol) {
        return this.roles.contains(rol);
    }

    public void asignarRol(Roles authority) {
        this.roles.add(authority);
    }

    @Override
    public boolean equals(Object o) {
        org.arqui.grupo9.microserviciocuentas.models.Usuario otroU = (org.arqui.grupo9.microserviciocuentas.models.Usuario) o;
        return this.getIdUsuario().equals(otroU.getIdUsuario());
    }
}

