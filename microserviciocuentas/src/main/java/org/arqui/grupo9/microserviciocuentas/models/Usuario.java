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

    private Set<Long> cuentasMp;

    @OneToMany
    private Set<CuentaSistema> cuentas;

    @ManyToMany(targetEntity = Roles.class, fetch = FetchType.LAZY)
    private Set<Roles> roles;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String nroCelular, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCelular = nroCelular;
        this.email = email;
        this.cuentasMp = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public boolean tieneCuenta(Long cuenta) {
        return this.cuentasMp.contains(cuenta);
    }

    public void vincularCuenta(Long cuenta) {
        this.cuentasMp.add(cuenta);
    }

    public void desvincularCuenta(Long cuenta) {
        this.cuentasMp.remove(cuenta);
    }

    public boolean tieneCuentasSistema() {
        return !this.cuentasMp.isEmpty();
    }

    public boolean tieneRol(Roles rol) {
        return this.roles.contains(rol);
    }

    public void asignarRol(Roles rol) {
        this.roles.add(rol);
    }

    @Override
    public boolean equals(Object o) {
        org.arqui.grupo9.microserviciocuentas.models.Usuario otroU = (org.arqui.grupo9.microserviciocuentas.models.Usuario) o;
        return this.getIdUsuario().equals(otroU.getIdUsuario());
    }
}

