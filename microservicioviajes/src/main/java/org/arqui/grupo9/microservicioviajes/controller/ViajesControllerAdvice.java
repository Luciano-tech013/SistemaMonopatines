package org.arqui.grupo9.microservicioviajes.controller;

import org.arqui.grupo9.microservicioviajes.services.dtos.ExcepcionDTO;
import org.arqui.grupo9.microservicioviajes.services.exceptions.*;
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
public class ViajesControllerAdvice {
    /**
     * Maneja la excepción {@code NotFoundViajeException}.
     * <p>
     * Este método intercepta la excepción {@code NotFoundViajeException} cuando un ID de viaje no es encontrado.
     * Se encarga de crear un DTO de excepcion que contiene un mensaje y un nivel de severidad, devolviendo esta información
     * al cliente junto con un código de estado 404 para indicar que el recurso no fue encontrado.
     * </p>
     *
     * @param ex La excepción {@code NotFoundViajeException} lanzada al no encontrar el viaje solicitado.
     *
     * @return {@code ResponseEntity<ExcepcionDTO>}: Un objeto especial de Spring Boot que envuelve el objeto de error de tipo
     * {@code ErrorDTO} y proporciona una respuesta estructurada en formato JSON, junto con el código de estado {@code HttpStatus.NOT_FOUND}.
     *
     * @see NotFoundViajeException
     * @see ExcepcionDTO
     * @see HttpStatus#NOT_FOUND
     */
    @ExceptionHandler(value = NotFoundViajeException.class)
    public ResponseEntity<ExcepcionDTO> notFoundViajeExceptionHandler(NotFoundViajeException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción {@code NotFoundUsuarioClientException}.
     * <p>
     * Este método intercepta la excepción {@code NotFoundUsuarioClientException} cuando no se encuentra al usuario
     * solicitado en el cliente, como podría ser el caso de un usuario no registrado o no encontrado. Devuelve una respuesta
     * {@code ResponseEntity} con un código de estado {@code 404 NOT_FOUND}, junto con un {@code ExcepcionDTO} que contiene
     * un mensaje y la severidad de la excepción.
     * </p>
     *
     * @param ex La excepción {@code NotFoundUsuarioClientException} lanzada cuando no se encuentra el usuario en el cliente.
     *
     * @return {@code ResponseEntity<ExcepcionDTO>}: Un objeto que encapsula el DTO de la excepción y un código de estado HTTP 404.
     */
    @ExceptionHandler(value = NotFoundUsuarioClientException.class)
    public ResponseEntity<ExcepcionDTO> notFoundUsuarioExceptionHandler(NotFoundUsuarioClientException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción {@code NotFoundMonopatinClientException}.
     * <p>
     * Este método intercepta la excepción {@code NotFoundMonopatinClientException} cuando no se encuentra el monopatín
     * solicitado, como podría ser el caso de un monopatín no disponible o no registrado. Devuelve una respuesta
     * {@code ResponseEntity} con un código de estado {@code 404 NOT_FOUND}, junto con un {@code ExcepcionDTO} que contiene
     * un mensaje y la severidad de la excepción.
     * </p>
     *
     * @param ex La excepción {@code NotFoundMonopatinClientException} lanzada cuando no se encuentra el monopatín solicitado.
     *
     * @return {@code ResponseEntity<ExcepcionDTO>}: Un objeto que encapsula el DTO de la excepción y un código de estado HTTP 404.
     */
    @ExceptionHandler(value = NotFoundMonopatinClientException.class)
    public ResponseEntity<ExcepcionDTO> notFoundMonopatinExceptionHandler(NotFoundMonopatinClientException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción {@code SaldoInsuficienteException}.
     * <p>
     * Este método intercepta la excepción {@code SaldoInsuficienteException} cuando un usuario intenta realizar una acción
     * que requiere más saldo del disponible. Devuelve una respuesta {@code ResponseEntity} con un código de estado
     * {@code 400 BAD_REQUEST}, junto con un {@code ExcepcionDTO} que contiene un mensaje y la severidad de la excepción.
     * </p>
     *
     * @param ex La excepción {@code SaldoInsuficienteException} lanzada cuando el saldo de un usuario es insuficiente para
     * realizar una operación.
     *
     * @return {@code ResponseEntity<ExcepcionDTO>}: Un objeto que encapsula el DTO de la excepción y un código de estado HTTP 400.
     */
    @ExceptionHandler(value = SaldoInsuficienteException.class)
    public ResponseEntity<ExcepcionDTO> saldoInsuficienteExceptionHandler(SaldoInsuficienteException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejador de excepciones para cuando ocurre un error relacionado con la finalización de un viaje.
     * Este método captura la excepción {@link FinalizarViajeException} y devuelve una respuesta con
     * un mensaje de error y un código de estado HTTP correspondiente a la naturaleza de la excepción.
     *
     * @param ex La excepción {@link FinalizarViajeException} que se lanzó durante la finalización de un viaje.
     * @return Una respuesta HTTP con el código de estado {@link HttpStatus#BAD_REQUEST} y un cuerpo
     *         que contiene un {@link ExcepcionDTO} con el mensaje de error y la severidad de la excepción.
     */
    @ExceptionHandler(value = FinalizarViajeException.class)
    public ResponseEntity<ExcepcionDTO> finalizarViajeExceptionHandler(FinalizarViajeException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
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
     * Manejador de excepciones para {@link CreditoInsuficienteException}.
     * <p>
     * Este método captura la excepción {@code CreditoInsuficienteException} lanzada cuando un usuario
     * intenta realizar una operación sin tener crédito suficiente, y devuelve una respuesta con un
     * código de estado {@code BAD_REQUEST} (400) junto con el mensaje de error amigable para el usuario
     * y el nivel de severidad de la excepción.
     * </p>
     *
     * @param ex La excepción {@link CreditoInsuficienteException} que se captura.
     * @return Un {@link ResponseEntity} con un objeto {@link ExcepcionDTO} que contiene el mensaje para el usuario
     *         y la severidad del error.
     */
    @ExceptionHandler(value = CreditoInsuficienteException.class)
    public ResponseEntity<ExcepcionDTO> creditoInsuficienteExceptionHandler(CreditoInsuficienteException ex) {
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
