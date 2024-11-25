package org.arqui.grupo9.microserviciocuentas.services.exceptions;

import lombok.Getter;

/**
 * Clase base para excepciones personalizadas en el sistema.
 * <p>
 * {@code MyException} extiende de {@link RuntimeException} y proporciona una estructura estándar para
 * excepciones que necesitan enviar un mensaje y una severidad específicos al usuario final. Esta clase es útil para manejar errores
 * que requieren más información contextual para que el usuario pueda entender lo que ocurrió y su gravedad.
 * </p>
 *
 * <ul>
 *     <li>{@code userMessage}: Mensaje amigable que explica el error de forma comprensible para el usuario final.</li>
 *     <li>{@code severity}: Nivel de severidad del error, proporcionando contexto sobre su impacto en el sistema.</li>
 * </ul>
 *
 * <p>
 * Las subclases de {@code MyException} pueden ser capturadas y manejadas en un controlador centralizado
 * que intercepta las excepciones, generando respuestas personalizadas con detalles específicos para el frontend.
 * </p>
 *
 */
@Getter
public class MyException extends RuntimeException {

    /**
     * Mensaje de error destinado al usuario final, explicando el error de manera comprensible.
     */
    private final String userMessage;

    /**
     * Nivel de severidad del error, utilizado para indicar la gravedad del problema.
     */
    private final String severity;

    /**
     * Crea una nueva instancia de {@code MyException} con el mensaje de error técnico,
     * el mensaje amigable para el usuario y el nivel de severidad.
     *
     * @param message Mensaje técnico para los registros internos del sistema.
     * @param userMessage Mensaje que será mostrado al usuario final.
     * @param severity Nivel de severidad del error.
     */
    public MyException(String message, String userMessage, String severity) {
        super(message);
        this.userMessage = userMessage;
        this.severity = severity;
    }
}

