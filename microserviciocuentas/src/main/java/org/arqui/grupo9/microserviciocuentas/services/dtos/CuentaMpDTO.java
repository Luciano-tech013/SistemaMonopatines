package org.arqui.grupo9.microserviciocuentas.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CuentaMpDTO {
    @NotNull(message = "El id no puede ser vacio")
    @Positive(message = "El id no puede ser negativo")
    private Long idCuentaMP;

    //@NotBlank(message = "El saldo enviado no es valido")
    @Positive(message = "El saldo no puede ser negativo")
    private Double saldo;

    private Set<Long> usuarios;

    public void desvincularUsuario(Long idUsuario) {
        this.usuarios.remove(idUsuario);
    }

    public void vincularUsuario(Long idUsuario) {
        this.usuarios.add(idUsuario);
    }
}
