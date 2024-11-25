package org.arqui.grupo9.microserviciocuentas.services.dtos;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class ExcepcionDTO implements Serializable {
    private final String userMessage;
    private final String severity;
}

