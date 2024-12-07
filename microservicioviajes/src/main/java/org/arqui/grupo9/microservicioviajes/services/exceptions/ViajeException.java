package org.arqui.grupo9.microservicioviajes.services.exceptions;

public class ViajeException extends MyException {
    public ViajeException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
