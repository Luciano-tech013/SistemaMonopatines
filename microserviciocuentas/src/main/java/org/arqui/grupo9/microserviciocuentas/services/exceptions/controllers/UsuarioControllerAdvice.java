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

@RestControllerAdvice
public class UsuarioControllerAdvice {
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
