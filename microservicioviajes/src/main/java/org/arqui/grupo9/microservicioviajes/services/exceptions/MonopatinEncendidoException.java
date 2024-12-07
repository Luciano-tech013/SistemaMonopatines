package org.arqui.grupo9.microservicioviajes.services.exceptions;

public class MonopatinEncendidoException extends MyException {
    public MonopatinEncendidoException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
