package org.arqui.grupo9.microservicioviajes.model.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcepcionDTO {
    private final String userMessage;
    private final String severity;
}

