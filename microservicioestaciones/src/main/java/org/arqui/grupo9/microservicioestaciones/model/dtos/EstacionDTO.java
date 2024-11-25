package org.arqui.grupo9.microservicioestaciones.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EstacionDTO {
    @NotNull(message = "El id no puede ser vacio")
    @Positive(message = "El id no puede ser negativo")
    private String idEstacion;

    @NotBlank(message = "El nombre no es valido")
    private String nombre;

    @NotBlank(message = "La latitud enviada no es valida")
    private Double latitud;

    @NotBlank(message = "La longitud enviada no es valida")
    private Double longitud;

    public EstacionDTO() {}
}
