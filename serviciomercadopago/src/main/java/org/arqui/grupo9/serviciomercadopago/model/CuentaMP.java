package org.arqui.grupo9.serviciomercadopago.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@Data
@Document(collection = "cuentas_mercadopago")
public class CuentaMP {
    @Id
    private Long idCuentaMP;

    @Field("saldo")
    private Double saldo;

    @Field("usuarios")
    private Set<Long> usuarios;

    public CuentaMP() {}

    public CuentaMP(Long idCuentaMP, Double saldo) {
        this.idCuentaMP = idCuentaMP;
        this.saldo = saldo;
        this.usuarios = new HashSet<>();
    }

    public boolean tieneUsuariosAsociados() {
        return !usuarios.isEmpty();
    }

    public boolean tieneUsuario(Long idUsuario) {
        return usuarios.contains(idUsuario);
    }

    public void asociarUsuario(Long idUsuario) {
        this.usuarios.add(idUsuario);
    }

    public void desvincularUsuario(Long idUsuario) {
        this.usuarios.remove(idUsuario);
    }
}
