package org.arqui.grupo9.microservicioviajes.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CuentaSistemaDTO {
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

    private LocalDate fechaInahilitada;

    private boolean inhabilitada;

    public boolean estaHabilitada() {
        if(this.fechaInahilitada == null)
            return true;

        if(this.fechaInahilitada.isEqual(LocalDate.now()) || this.fechaInahilitada.isBefore(LocalDate.now())) {
            setInhabilitada(false);
            setFechaInahilitada(null);
            return true;
        }

        return false;
    }
}
