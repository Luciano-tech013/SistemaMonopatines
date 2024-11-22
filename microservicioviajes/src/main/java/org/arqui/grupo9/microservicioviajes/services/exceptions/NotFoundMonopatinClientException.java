package org.arqui.grupo9.microservicioviajes.services.exceptions;

/**
 * Excepción personalizada que indica que no se ha encontrado el monopatín solicitado en el sistema.
 * <p>
 * Esta excepción se lanza cuando se intenta acceder a un monopatín por su identificador y no se encuentra
 * en la base de datos. Proporciona detalles específicos, incluyendo un mensaje técnico para los logs
 * internos, un mensaje para el usuario y un nivel de severidad del error, que se envía al frontend para
 * informar al usuario.
 * </p>
 *
 * @see MyException
 */
public class NotFoundMonopatinClientException extends MyException {

    /**
     * Constructor de la excepción que inicializa los mensajes y la severidad.
     *
     * @param message Mensaje técnico de la excepción para los logs del sistema.
     * @param userMessage Mensaje que será enviado al usuario, explicando que no se encontró el monopatín.
     * @param severity Nivel de severidad de la excepción, indicando la gravedad del error.
     */
    public NotFoundMonopatinClientException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}
