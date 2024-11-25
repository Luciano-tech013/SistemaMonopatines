package org.arqui.grupo9.microservicioestaciones.service.exception;

/**
 * Excepción personalizada que indica que no se ha encontrado la estacion solicitada en el sistema.
 * <p>
 * Esta excepción se lanza cuando se intenta acceder a una estacion por su identificador y no se encuentra
 * en la base de datos. Proporciona detalles específicos, incluyendo un mensaje técnico para los logs
 * internos, un mensaje para el usuario y un nivel de severidad del error, que se envía al frontend para
 * informar al usuario.
 * </p>
 *
 * @see MyException
 */
public class NotFoundEstacionException extends MyException {
    /**
     * Crea una nueva instancia de {@code MyException} con el mensaje de error técnico,
     * el mensaje amigable para el usuario y el nivel de severidad.
     *
     * @param message     Mensaje técnico para los registros internos del sistema.
     * @param userMessage Mensaje que será mostrado al usuario final.
     * @param severity    Nivel de severidad del error.
     */
    public NotFoundEstacionException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
