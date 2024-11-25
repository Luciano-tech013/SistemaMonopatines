package org.arqui.grupo9.microserviciocuentas.services.exceptions;

public class DeleteUsuarioException extends MyException {
    public DeleteUsuarioException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
