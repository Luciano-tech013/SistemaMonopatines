package org.arqui.grupo9.serviciomercadopago.service.exceptions;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {
    private final String userMessage;
    private final String severity;

    public MyException(String message, String userMessage, String severity) {
        super(message);
        this.userMessage = userMessage;
        this.severity = severity;
    }
}

