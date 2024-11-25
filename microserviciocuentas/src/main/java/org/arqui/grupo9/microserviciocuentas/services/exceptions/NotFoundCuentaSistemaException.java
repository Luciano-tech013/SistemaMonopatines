package org.arqui.grupo9.microserviciocuentas.services.exceptions;

public class NotFoundCuentaSistemaException extends MyException {
    public NotFoundCuentaSistemaException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
