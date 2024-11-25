package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CuentaSistemaRequestDTO implements Serializable {
    @NotBlank(message = "El username de la cuenta no es valido")
    private String username;

    @NotBlank(message = "El password de la cuenta no es valido")
    private String password;

    @NotNull(message = "No se puede crear una cuenta sin asignarle un usuario existente en el sistema. Por favor, registrate en el sistema")
    private Long idUsuario;

    @NotNull(message = "No puede crear una cuenta sin asociarla a una cuenta de Mercado Pago existente")
    private Long IdCuentaMp;
}
