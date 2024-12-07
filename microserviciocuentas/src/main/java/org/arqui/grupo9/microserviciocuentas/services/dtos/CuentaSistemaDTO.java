package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CuentaSistemaDTO implements Serializable {
    @NotBlank(message = "El username de la cuenta no es valido")
    private String username;

    @NotBlank(message = "El password de la cuenta no es valido")
    private String password;

    @NotNull(message = "No se puede crear una cuenta sin asignarle un usuario existente en el sistema. Por favor, registrate en el sistema")
    @Positive(message = "El id del usuario no puede ser negativo")
    private Long idUsuario;

    @NotNull(message = "No puede crear una cuenta sin asociarla a una cuenta de Mercado Pago existente")
    @Positive(message = "El id de la cuenta no puede ser negativo")
    private Long idCuenta;
}
