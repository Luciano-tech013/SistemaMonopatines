package org.arqui.grupo9.microserviciocuentas.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UsuarioWithAuthoritiesDTO {
    private String email;
    private String password;
    private List<String> authorities;
}
