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

    @OneToMany(targetEntity = CuentaSistema.class, fetch = FetchType.LAZY, mappedBy = "cuentaMp")
    private Set<CuentaSistema> cuentasSistema;

    public CuentaMP() {}

    public CuentaMP(Long idCuentaMP, Double saldo) {
        this.idCuentaMP = idCuentaMP;
        this.saldo = saldo;
        this.cuentasSistema = new HashSet<>();
    }

    public boolean tieneCuentasSistema() {
        return !this.cuentasSistema.isEmpty();
    }

    public void asociarCuentaSistema(CuentaSistema cuentaSistema) {
        this.cuentasSistema.add(cuentaSistema);
    }

    public void desvincularCuentaSistema(CuentaSistema cuentaSistema) {
        this.cuentasSistema.remove(cuentaSistema);
    }
}
