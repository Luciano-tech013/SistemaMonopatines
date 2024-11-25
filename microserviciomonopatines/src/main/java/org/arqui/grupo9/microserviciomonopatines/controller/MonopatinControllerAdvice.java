package org.arqui.grupo9.microserviciomonopatines.controller;

import org.arqui.grupo9.microserviciomonopatines.service.dto.ExcepcionDTO;
import org.arqui.grupo9.microserviciomonopatines.service.exceptions.NotFoundMonopatinException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase de manejo de excepciones para servicios de la aplicación.
 * <p>
 * Esta clase actúa como un interceptador de excepciones lanzadas dentro de los servicios,
 * permitiendo centralizar el manejo de errores específicos y proporcionando respuestas personalizadas al cliente.
 * Utiliza {@link RestControllerAdvice} para capturar excepciones de forma global en la aplicación.
 * </p>
 *
 * <p>
 * De esta forma se evita realizar verificaciones extras sobre los controladores. Con esta implementacion solo se retornan respuesta validas
 * </p>
 *
 * @see RestControllerAdvice
 */
@RestControllerAdvice
public class MonopatinControllerAdvice {

    /**
     * Maneja la excepción {@link NotFoundMonopatinException} lanzada cuando no se encuentra un monopatín
     * en el sistema. Este método captura la excepción y devuelve una respuesta con el mensaje de error
     * adecuado y el nivel de severidad en formato de una entidad {@link ExcepcionDTO}.
     *
     * @param ex La excepción {@link NotFoundMonopatinException} que se ha lanzado.
     * @return Una respuesta HTTP {@link ResponseEntity} con un código de estado {@link HttpStatus#NOT_FOUND},
     *         que incluye el {@link ExcepcionDTO} con el mensaje para el usuario y la severidad del error.
     */
    @ExceptionHandler(value = NotFoundMonopatinException.class)
    public ResponseEntity<ExcepcionDTO> notFoundMonopatinExceptionHandler(NotFoundMonopatinException ex) {
        return new ResponseEntity<ExcepcionDTO>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    /**
     * Método auxiliar para crear un {@code ExcepcionDTO}.
     * <p>
     * Este método se utiliza para crear un objeto {@code ExcepcionDTO} con un mensaje y severidad proporcionados, que luego
     * será enviado al cliente como parte de la respuesta.
     * </p>
     *
     * @param message El mensaje que será mostrado al usuario en caso de la excepción.
     * @param severity El nivel de severidad de la excepción, que indica la gravedad del error.
     *
     * @return {@code ExcepcionDTO}: El objeto DTO que encapsula el mensaje y severidad para la respuesta.
     */
    private ExcepcionDTO createExceptionDTO(String message, String severity) {
        return new ExcepcionDTO(message, severity);
    }
}
