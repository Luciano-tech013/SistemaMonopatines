package org.arqui.grupo9.serviciomercadopago.service.exceptions;

public class NotFoundCuentaMPException extends MyException {
  public NotFoundCuentaMPException(String message, String userMessage, String severity) {
    super(message, userMessage, severity);
  }
}


