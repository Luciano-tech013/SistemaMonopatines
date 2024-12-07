package org.arqui.grupo9.serviciomercadopago.service.controller;

import org.arqui.grupo9.serviciomercadopago.service.dto.ExcepcionDTO;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.DeleteCuentaMPException;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.NotFoundCuentaMPException;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.NotFoundUsuarioException;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.UsuarioYaRegistradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
public class CuentaMpControllerAdvice {
    @ExceptionHandler(value = NotFoundCuentaMPException.class)
    public ResponseEntity<ExcepcionDTO> notFoundCuentaMPExceptionHandler(NotFoundCuentaMPException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DeleteCuentaMPException.class)
    public ResponseEntity<ExcepcionDTO> deleteCuentaMPExceptionHandler(DeleteCuentaMPException ex) {
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsuarioYaRegistradoException.class)
    public ResponseEntity<ExcepcionDTO> usuarioYaRegistradoExceptionHandler(UsuarioYaRegistradoException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundUsuarioException.class)
    public ResponseEntity<ExcepcionDTO> notFoundUsuarioExceptionHandler(NotFoundUsuarioException ex) {
        ExcepcionDTO exception = new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
