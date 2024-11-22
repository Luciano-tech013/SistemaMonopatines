package org.arqui.grupo9.microservicioviajes.services.exceptions;

/**
 * Excepción personalizada que indica que el saldo disponible es insuficiente para completar una operación.
 * <p>
 * Esta excepción se lanza cuando un usuario intenta realizar una acción que requiere un saldo superior al que
 * tiene disponible. Esto puede ocurrir en situaciones como el intento de realizar un pago o activar un servicio
 * (como un monopatín) cuando el saldo de la cuenta del usuario es insuficiente para cubrir el costo.
 * La clase extiende {@code MyException} para incluir detalles adicionales como el mensaje técnico, el mensaje
 * que será enviado al usuario y el nivel de severidad del error.
 * </p>
 *
 * @see MyException
 */
public class SaldoInsuficienteException extends MyException {

    /**
     * Constructor de la excepción que inicializa los mensajes y la severidad.
     *
     * @param message Mensaje técnico de la excepción para los registros del sistema, proporcionando detalles sobre el error.
     * @param userMessage Mensaje amigable para el usuario, explicando que no tiene suficiente saldo para completar la acción.
     * @param severity Nivel de severidad de la excepción, indicando la gravedad del error.
     */
    public SaldoInsuficienteException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}

