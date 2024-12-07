package org.arqui.grupo9.microservicioviajes.services.exceptions;

public class NotValidCodigoQRException extends MyException {
    public NotValidCodigoQRException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
