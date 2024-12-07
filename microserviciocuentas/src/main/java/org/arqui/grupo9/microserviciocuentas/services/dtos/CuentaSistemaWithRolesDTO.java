package org.arqui.grupo9.microserviciocuentas.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.arqui.grupo9.microserviciocuentas.models.Roles;
import java.io.Serializable;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CuentaSistemaWithRolesDTO implements Serializable {
    private String username;
    private String password;
    private Set<Roles> roles;
}
