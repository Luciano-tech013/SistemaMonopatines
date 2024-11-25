package org.arqui.grupo9.microserviciocuentas.services.exceptions.controllers;

import org.arqui.grupo9.microserviciocuentas.services.dtos.ExcepcionDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.DeleteUsuarioException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundFechaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundUsuarioException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.UsuarioYaRegistradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase de manejo de excepciones para usuarioService de la aplicación.
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
public class UsuarioControllerAdvice {
    /**
     * Maneja la excepción NotFoundUsuarioException.
     *
     * <p>Este método se activa cuando ocurre una excepción {@code NotFoundUsuarioException},
     * indicando que no se encontró el usuario solicitado. La respuesta tiene un estado
     * HTTP de {@code 400 BAD REQUEST}.</p>
     *
     * @param ex Excepción {@code NotFoundUsuarioException} que proporciona información
     *           sobre el usuario no encontrado.
     * @return {@code ResponseEntity<ExcepcionDTO>} que contiene el mensaje de usuario
     *         y severidad de la excepción.
     */
    @ExceptionHandler(value = NotFoundUsuarioException.class)
    public ResponseEntity<ExcepcionDTO> notFoundUsuarioExceptionHandler(NotFoundUsuarioException ex) {
        ExcepcionDTO exception = new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DeleteUsuarioException.class)
    public ResponseEntity<ExcepcionDTO> deleteUsuarioExceptionHandler(DeleteUsuarioException ex) {
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }
}
