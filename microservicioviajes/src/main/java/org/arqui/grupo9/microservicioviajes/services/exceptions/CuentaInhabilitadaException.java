package org.arqui.grupo9.microservicioviajes.services.exceptions;

public class CuentaInhabilitadaException extends MyException {
  public CuentaInhabilitadaException(String message, String userMessage, String severity) {
    super(message, userMessage, severity);
  }
}

