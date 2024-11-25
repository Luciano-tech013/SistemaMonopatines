package org.arqui.grupo9.microserviciocuentas.services.exceptions;

public class CuentaMPYaRegistradaException extends MyException {
    public CuentaMPYaRegistradaException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
