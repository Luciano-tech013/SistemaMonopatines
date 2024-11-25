package org.arqui.grupo9.microserviciocuentas.services.exceptions.controllers;

import org.arqui.grupo9.microserviciocuentas.services.dtos.ExcepcionDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.DeleteCuentaMPException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundCuentaMPException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase de manejo de excepciones para cuentaMpService de la aplicación.
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
@ResponseBody
public class CuentaMpControllerAdvice {
    /**
     * Maneja la excepción NotFoundCuentaException.
     *
     * <p>Este método se ejecuta cuando se lanza una excepción de tipo
     * {@code NotFoundCuentaException} y genera una respuesta HTTP con el código
     * de estado {@code 404 NOT FOUND}.</p>
     *
     * @param ex Excepción {@code NotFoundCuentaException} que contiene
     *           detalles sobre la cuenta que no fue encontrada.
     * @return {@code ResponseEntity<ExcepcionDTO>} que contiene el mensaje de
     *         usuario y la severidad de la excepción.
     */
    @ExceptionHandler(value = NotFoundCuentaMPException.class)
    public ResponseEntity<ExcepcionDTO> notFoundCuentaMPExceptionHandler(NotFoundCuentaMPException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DeleteCuentaMPException.class)
    public ResponseEntity<ExcepcionDTO> deleteCuentaMPExceptionHandler(DeleteCuentaMPException ex) {
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }
}
