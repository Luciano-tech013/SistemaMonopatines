package org.arqui.grupo9.microservicioviajes.services.exceptions;

/**
 * Excepción lanzada cuando se intenta realizar una operación que requiere crédito suficiente
 * y el usuario no tiene el saldo necesario para completarla.
 * <p>
 * Esta excepción extiende de {@code MyException} y permite especificar un mensaje técnico,
 * un mensaje amigable para el usuario y el nivel de severidad de la excepción.
 * </p>
 *
 * @see MyException
 */
public class CreditoInsuficienteException extends MyException {

    /**
     * Crea una nueva instancia de {@code CreditoInsuficienteException} con el mensaje de error técnico,
     * el mensaje amigable para el usuario y el nivel de severidad del error.
     *
     * @param message     Mensaje técnico para los registros internos del sistema.
     * @param userMessage Mensaje que será mostrado al usuario final.
     * @param severity    Nivel de severidad del error.
     */
    public CreditoInsuficienteException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}

