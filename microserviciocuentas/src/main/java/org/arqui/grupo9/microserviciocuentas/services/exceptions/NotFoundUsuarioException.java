package org.arqui.grupo9.microserviciocuentas.services.exceptions;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un usuario en el sistema.
 *
 * <p>Esta excepción indica que se intentó acceder a un usuario que no existe o que no
 * está registrado en el sistema. Puede utilizarse en los servicios de cuenta o usuario para
 * manejar situaciones en las que un usuario no ha sido localizado durante la ejecución
 * de una operación.
 *
 * <p>Esta excepción extiende la clase {@code MyException}, proporcionando información
 * adicional para los mensajes de error orientados al usuario, así como la severidad
 * del error.
 *
 */
public class NotFoundUsuarioException extends MyException {
    public NotFoundUsuarioException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
