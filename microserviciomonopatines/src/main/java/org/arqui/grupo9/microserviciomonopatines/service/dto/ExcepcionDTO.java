package org.arqui.grupo9.microserviciomonopatines.service.dto;

import lombok.AllArgsConstructor;

/**
 * La clase {@code ExcepcionDTO} representa un objeto de transferencia de datos (DTO) que contiene la información
 * sobre la excepción que será enviada al frontend para notificar al usuario sobre lo ocurrido en el sistema.
 * Esta clase contiene dos atributos principales: un mensaje informativo para el usuario y un indicador de severidad
 * que refleja la gravedad del error.
 *
 * <p>Este DTO se utiliza en el contexto de manejo de excepciones, proporcionando detalles sobre el error que
 * ocurrió en el backend y permitiendo que el frontend muestre una respuesta adecuada al usuario.</p>
 */
@AllArgsConstructor
public class ExcepcionDTO {

    /**
     * Mensaje informativo que será enviado al frontend para ser mostrado al usuario.
     * Este mensaje describe lo sucedido y ayuda al usuario a entender el problema.
     * El valor de este atributo es obtenido de la excepción lanzada.
     */
    private final String userMessage;

    /**
     * Severidad de la excepción que será enviado al frontend para indicar la gravedad del error.
     * El valor de este atributo se obtiene también de la excepción lanzada.
     * Puede ser un valor como "ALTA", "MEDIA", "BAJA", dependiendo del tipo de error.
     */
    private final String severity;
}

