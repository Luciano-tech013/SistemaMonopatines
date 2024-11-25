package org.arqui.grupo9.microserviciocuentas.services.exceptions;

/**
 * Excepción personalizada que se lanza cuando el sistema no puede completar el registro de un usuario.
 *
 * <p>Esta excepción indica que hubo un fallo durante el intento de registrar un nuevo usuario
 * en el sistema, debido a algún problema interno o externo que impidió el éxito de la operación.
 * Puede usarse en servicios de usuario para manejar errores específicos de registro.
 *
 * <p>Extiende la clase {@code MyException}, lo que permite incluir un mensaje técnico, un mensaje
 * orientado al usuario y un nivel de severidad, facilitando el manejo de excepciones en la aplicación.
 *
 */
public class CouldNotRegisterException extends MyException {
    public CouldNotRegisterException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}

