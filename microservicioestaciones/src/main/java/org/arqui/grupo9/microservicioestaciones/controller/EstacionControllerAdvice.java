package org.arqui.grupo9.microservicioestaciones.controller;

import org.arqui.grupo9.microservicioestaciones.model.dtos.ExcepcionDTO;
import org.arqui.grupo9.microservicioestaciones.service.exception.NotFoundEstacionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EstacionControllerAdvice {
    @ExceptionHandler(value = NotFoundEstacionException.class)
    public ResponseEntity<ExcepcionDTO> notFoundEstacionExceptionHanlder(NotFoundEstacionException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
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
