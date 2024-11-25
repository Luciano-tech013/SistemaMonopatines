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

    @Column(nullable = false)
    private String password;

    @ManyToMany(targetEntity = CuentaMP.class, mappedBy = "usuarios",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CuentaMP> cuentasMp;

    @ManyToMany(targetEntity = Roles.class, fetch = FetchType.LAZY)
    private Set<Roles> roles;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String apellido, String nroCelular, String email, String password) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCelular = nroCelular;
        this.email = email;
        this.password = password;
        this.cuentasMp = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public Usuario(String nombre, String apellido, String nroCelular, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCelular = nroCelular;
        this.email = email;
        this.cuentasMp = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public boolean tieneCuenta(CuentaMP cuenta) {
        return this.cuentasMp.contains(cuenta);
    }

    public void asociarCuenta(CuentaMP cuenta) {
        this.cuentasMp.add(cuenta);
    }

    public void eliminarCuenta(CuentaMP cuenta) {
        this.cuentasMp.remove(cuenta);
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

