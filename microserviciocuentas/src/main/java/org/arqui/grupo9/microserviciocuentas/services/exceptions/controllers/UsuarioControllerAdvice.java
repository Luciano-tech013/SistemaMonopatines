package org.arqui.grupo9.microserviciocuentas.services.exceptions.controllers;

import org.arqui.grupo9.microserviciocuentas.services.dtos.ExcepcionDTO;
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
        ExcepcionDTO exception = this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja la excepción UsuarioYaRegistradoException.
     *
     * <p>Este método gestiona la excepción {@code UsuarioYaRegistradoException},
     * que indica que el usuario ya existe en el sistema. Devuelve una respuesta con
     * el código de estado {@code 400 BAD REQUEST}.</p>
     *
     * @param ex Excepción {@code UsuarioYaRegistradoException} con detalles sobre
     *           el intento de registro duplicado.
     * @return {@code ResponseEntity<ExcepcionDTO>} que contiene el mensaje de usuario
     *         y la severidad de la excepción.
     */
    @ExceptionHandler(value = UsuarioYaRegistradoException.class)
    public ResponseEntity<ExcepcionDTO> usuarioYaRegistradoExceptionHandler(UsuarioYaRegistradoException ex) {
        return new ResponseEntity<ExcepcionDTO>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejador de excepciones para fechas no encontradas en el sistema.
     * <p>
     * Este método se ejecuta cuando se lanza una excepción de tipo {@code NotFoundFechaException}
     * debido a que un mes o año solicitado no está registrado en el sistema.
     * Al interceptar esta excepción, se construye un objeto {@code ExcepcionDTO} con un mensaje
     * amigable para el usuario y el nivel de severidad, y se devuelve una respuesta con el estado
     * {@code HttpStatus.BAD_REQUEST}.
     * </p>
     *
     * @param ex La excepción {@code NotFoundFechaException} que fue lanzada.
     * @return Un objeto {@code ResponseEntity<ExcepcionDTO>} que contiene el mensaje para el usuario
     * y la severidad de la excepción, junto con un código de estado HTTP 400 (BAD_REQUEST).
     */
    @ExceptionHandler(value = NotFoundFechaException.class)
    public ResponseEntity<ExcepcionDTO> notFoundFechaExceptionHandler(NotFoundFechaException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
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
