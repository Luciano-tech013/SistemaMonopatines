package org.arqui.grupo9.gateway.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;

@Getter
@AllArgsConstructor
public class UsuarioDTO {
    private String email;
    private String password;
    private Set<String> authorities;
}
