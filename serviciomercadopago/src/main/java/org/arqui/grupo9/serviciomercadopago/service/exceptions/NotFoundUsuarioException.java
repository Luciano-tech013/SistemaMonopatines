package org.arqui.grupo9.serviciomercadopago.service.exceptions;

public class NotFoundUsuarioException extends MyException {
    public NotFoundUsuarioException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
