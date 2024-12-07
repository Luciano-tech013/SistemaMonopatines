package org.arqui.grupo9.microservicioviajes.services.exceptions;

/**
 * Excepción personalizada que indica que no se ha encontrado el usuario especificado en el cliente.
 * <p>
 * Esta excepción se lanza cuando se intenta acceder a un usuario en un sistema externo (por ejemplo, un servicio
 * de cliente mediante FeignClient) y este usuario no está disponible o no es encontrado. La clase extiende
 * de {@code MyException} para aprovechar la estructura de excepción personalizada, que incluye un mensaje técnico,
 * un mensaje para el usuario y un nivel de severidad.
 * </p>
 *
 * @see MyException
 */
public class NotFoundCuentaClientException extends MyException {

    /**
     * Constructor de la excepción que inicializa los mensajes y la severidad.
     *
     * @param message Mensaje técnico de la excepción para los registros del sistema.
     * @param userMessage Mensaje amigable para el usuario, informando que no se encontró el usuario en el sistema.
     * @param severity Nivel de severidad de la excepción, indicando la gravedad del error.
     */
    public NotFoundCuentaClientException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}

