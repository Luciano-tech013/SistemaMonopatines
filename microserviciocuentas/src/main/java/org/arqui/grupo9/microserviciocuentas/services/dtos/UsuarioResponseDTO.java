package org.arqui.grupo9.microserviciocuentas.services.dtos;

import lombok.Getter;
import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;

import java.util.Set;

@Getter
public class UsuarioResponseDTO extends UsuarioRequestDTO{
    private Set<CuentaSistema> cuentasSistema;

    public UsuarioResponseDTO(String nombre, String apellido, String nroCelular, String email, Set<CuentaSistema> cuentasSistema) {
        super(nombre, apellido, nroCelular, email);
        this.cuentasSistema = cuentasSistema;
    }
}
