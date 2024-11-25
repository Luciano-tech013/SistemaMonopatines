package org.arqui.grupo9.microserviciocuentas.services.exceptions;

public class NotFoundRolException extends MyException {
    public NotFoundRolException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
