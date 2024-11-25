package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaMpDTO {
    private Long idCuentaMP;

    //@NotBlank(message = "El saldo enviado no es valido")
    @Positive(message = "El saldo no puede ser negativo")
    private Double saldo;
}
