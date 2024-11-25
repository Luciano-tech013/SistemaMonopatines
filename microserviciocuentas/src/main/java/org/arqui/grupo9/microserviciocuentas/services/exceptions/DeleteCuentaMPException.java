package org.arqui.grupo9.microserviciocuentas.services.exceptions;

public class DeleteCuentaMPException extends MyException {
    public DeleteCuentaMPException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
