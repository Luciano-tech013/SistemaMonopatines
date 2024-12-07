package org.arqui.grupo9.serviciomercadopago.service.exceptions;

public class UsuarioYaRegistradoException extends MyException {
    public UsuarioYaRegistradoException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}


