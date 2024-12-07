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

    @ExceptionHandler(value = CuentaInhabilitadaException.class)
    public ResponseEntity<ExcepcionDTO> cuentaInhabilitadaExceptionHandler(CuentaInhabilitadaException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundFechaException.class)
    public ResponseEntity<ExcepcionDTO> notFoundFechaExceptionHandler(NotFoundFechaException ex) {
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsuarioYaRegistradoException.class)
    public ResponseEntity<ExcepcionDTO> usuarioYaRegistradoExceptionHandler(UsuarioYaRegistradoException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }
}
