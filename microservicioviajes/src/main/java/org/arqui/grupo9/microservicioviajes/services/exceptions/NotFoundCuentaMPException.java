package org.arqui.grupo9.microservicioviajes.services.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra una cuenta en el sistema.
 *
 * <p>Esta excepción indica que se intentó acceder a una cuenta que no existe o que no
 * está registrada en el sistema. Puede utilizarse en los servicios de usuario para
 * manejar situaciones en las que una cuenta no ha sido localizada durante la ejecución
 * de una operación.
 *
 * <p>Esta excepción extiende la clase {@code MyException}, proporcionando información
 * adicional para los mensajes de error orientados al usuario, así como la severidad
 * del error.
 *
 */
public class NotFoundCuentaMPException extends MyException {
  public NotFoundCuentaMPException(String message, String userMessage, String severity) {
    super(message, userMessage, severity);
  }
}


