package org.arqui.grupo9.microserviciocuentas.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CuentaSistemaResponseDTO {
    private String username;
    private String password;
    private Set<Usuario> usuarios;
    private CuentaMP cuentaMp;
}
