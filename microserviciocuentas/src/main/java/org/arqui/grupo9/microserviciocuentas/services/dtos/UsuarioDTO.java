package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    @NotNull(message = "El id ingresado no puede ser vacio")
    @Positive(message = "El id debe ser positivo")
    private Long id;

    @NotBlank(message = "El nombre ingresado es invalido")
    private String nombre;

    @NotBlank(message = "El apellido ingresado es invalido")
    private String apellido;

    @NotBlank(message = "El nro de celular es invalido")
    private String nroCelular;

    @NotBlank(message = "El email ingresado es invalido")
    private String email;

    @NotBlank(message = "La contraseña es invalida")
    private String password;
}
