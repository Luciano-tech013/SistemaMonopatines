package org.arqui.grupo9.microserviciocuentas.services.exceptions.controllers;

import org.arqui.grupo9.microserviciocuentas.services.dtos.ExcepcionDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.CouldNotRegisterException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.CuentaInhabilitadaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundCuentaException;
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
    @ExceptionHandler(value = NotFoundCuentaException.class)
    public ResponseEntity<ExcepcionDTO> notFoundCuentaExceptionHandler(NotFoundCuentaException ex) {
        return new ResponseEntity<ExcepcionDTO>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
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
        return new ResponseEntity<ExcepcionDTO>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja la excepción CouldNotRegisterException.
     *
     * <p>Este método responde a excepciones de tipo {@code CouldNotRegisterException},
     * indicando que ocurrió un error al intentar registrar un nuevo recurso o entidad.
     * La respuesta tiene un código de estado HTTP {@code 400 BAD REQUEST}.</p>
     *
     * @param ex Excepción {@code CouldNotRegisterException} que describe el fallo de
     *           registro.
     * @return {@code ResponseEntity<ExcepcionDTO>} con los detalles del mensaje de usuario
     *         y severidad de la excepción.
     */
    @ExceptionHandler(value = CouldNotRegisterException.class)
    public ResponseEntity<ExcepcionDTO> couldNotRegisterExceptionHandler(CouldNotRegisterException ex) {
        return new ResponseEntity<ExcepcionDTO>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
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
