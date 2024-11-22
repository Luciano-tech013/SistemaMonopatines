package org.arqui.grupo9.microservicioviajes.services.exceptions;

import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Excepción personalizada que se lanza cuando no se encuentra un viaje en el sistema.
 * <p>
 * La clase {@code NotFoundViajeException} extiende de {@link MyException} y representa un error específico
 * de "recurso no encontrado". Es utilizada para indicar que un ID de viaje proporcionado no existe en la base de datos
 * o el sistema, devolviendo un mensaje personalizado al usuario y un nivel de severidad para gestionar la respuesta.
 * </p>
 *
 * <p>
 * Esta excepción puede ser capturada y manejada en un controlador con un manejador de excepciones,
 * devolviendo al cliente una respuesta HTTP adecuada, como el estado 404 (Not Found).
 * </p>
 *
 * @see MyException
 * @see ControllerAdvice
 */
@Getter
public class NotFoundViajeException extends MyException {

    /**
     * Constructor para crear una instancia de {@code NotFoundViajeException}.
     *
     * @param message     Mensaje técnico de error que puede ser registrado para el equipo de desarrollo.
     * @param userMessage Mensaje orientado al usuario final, explicando el error de manera comprensible.
     * @param severity    Nivel de severidad del error, utilizado para evaluar su impacto en el sistema.
     */
    public NotFoundViajeException(String message, String userMessage, String severity) {
        super(message, userMessage, severity);
    }
}

