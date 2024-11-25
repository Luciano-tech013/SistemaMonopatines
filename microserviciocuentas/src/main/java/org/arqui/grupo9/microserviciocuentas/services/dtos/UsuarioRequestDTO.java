package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;

import java.io.Serializable;
import java.util.Set;

@Getter
@AllArgsConstructor
public class UsuarioRequestDTO implements Serializable {
    @NotBlank(message = "El nombre ingresado es invalido")
    private String nombre;

    @NotBlank(message = "El apellido ingresado es invalido")
    private String apellido;

    @NotBlank(message = "El nro de celular es invalido")
    private String nroCelular;

    @NotBlank(message = "El email ingresado es invalido")
    private String email;
}
