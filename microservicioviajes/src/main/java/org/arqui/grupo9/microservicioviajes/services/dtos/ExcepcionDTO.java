package org.arqui.grupo9.microservicioviajes.services.dtos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExcepcionDTO {
    private final String userMessage;
    private final String severity;
}

