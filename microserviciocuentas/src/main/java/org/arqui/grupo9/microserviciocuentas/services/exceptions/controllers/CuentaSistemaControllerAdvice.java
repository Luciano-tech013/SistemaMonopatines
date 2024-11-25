package org.arqui.grupo9.microserviciocuentas.services.exceptions.controllers;

import org.arqui.grupo9.microserviciocuentas.services.dtos.ExcepcionDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.CuentaInhabilitadaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.CuentaMPYaRegistradaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundFechaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.UsuarioYaRegistradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CuentaSistemaControllerAdvice {

    @ExceptionHandler(value = CuentaMPYaRegistradaException.class)
    public ResponseEntity<ExcepcionDTO> cuentaMPYaRegistradaExceptionHandler(CuentaMPYaRegistradaException ex) {
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja la excepción CuentaInhabilitadaException.
     *
     * <p>Este método maneja excepciones {@code CuentaInhabilitadaException}, indicando
     * que la cuenta se encuentra inhabilitada. Responde con un estado HTTP {@code 400 BAD REQUEST}.</p>
     *
     * @param ex Excepción {@code CuentaInhabilitadaException} que contiene detalles sobre
     *           la inhabilitación de la cuenta.
     * @return {@code ResponseEntity<ExcepcionDTO>} con el mensaje para el usuario
     *         y la severidad de la excepción.
     */
    @ExceptionHandler(value = CuentaInhabilitadaException.class)
    public ResponseEntity<ExcepcionDTO> cuentaInhabilitadaExceptionHandler(CuentaInhabilitadaException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }
}
