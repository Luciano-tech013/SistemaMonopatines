package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaMpDTO {
    @NotNull(message = "El id no puede ser vacio")
    @Positive(message = "El id no puede ser negativo")
    private Long idCuentaMP;

    //@NotBlank(message = "El saldo enviado no es valido")
    @Positive(message = "El saldo no puede ser negativo")
    private Double saldo;
}
