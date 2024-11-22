package org.arqui.grupo9.microservicioviajes.services.exceptions;

/**
 * Excepción personalizada que se lanza cuando ocurre un error al intentar finalizar un viaje.
 * Esta excepción es utilizada para capturar y manejar situaciones en las que no es posible completar el proceso de finalización del viaje,
 * como cuando un viaje se intenta finalizar y no ah sido generado aun.
 *
 * <p>Extiende la clase {@link MyException} para proporcionar información adicional sobre el error,
 * incluyendo el mensaje técnico, el mensaje que se muestra al usuario y la severidad del problema.</p>
 */
public class FinalizarViajeException extends MyException {

    /**
     * Constructor de la excepción {@code FinalizarViajeException}.
     *
     * @param message El mensaje técnico que describe el error de forma interna, utilizado para los desarrolladores.
     * @param userMessage El mensaje que se mostrará al usuario final, explicando el error de manera comprensible.
     * @param severity La severidad del error, lo que ayuda a determinar la gravedad del problema (por ejemplo, {@code "high"}, {@code "medium"}, {@code "low"}).
     */
    public FinalizarViajeException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}

