package org.arqui.grupo9.microservicioviajes.controller;

import org.arqui.grupo9.microservicioviajes.services.dtos.ExcepcionDTO;
import org.arqui.grupo9.microservicioviajes.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ViajesControllerAdvice {

    @ExceptionHandler(value = NotFoundViajeException.class)
    public ResponseEntity<ExcepcionDTO> notFoundViajeExceptionHandler(NotFoundViajeException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundCuentaClientException.class)
    public ResponseEntity<ExcepcionDTO> notFoundUsuarioExceptionHandler(NotFoundCuentaClientException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundMonopatinClientException.class)
    public ResponseEntity<ExcepcionDTO> notFoundMonopatinExceptionHandler(NotFoundMonopatinClientException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SaldoInsuficienteException.class)
    public ResponseEntity<ExcepcionDTO> saldoInsuficienteExceptionHandler(SaldoInsuficienteException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FinalizarViajeException.class)
    public ResponseEntity<ExcepcionDTO> finalizarViajeExceptionHandler(FinalizarViajeException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundFechaException.class)
    public ResponseEntity<ExcepcionDTO> notFoundFechaExceptionHandler(NotFoundFechaException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CreditoInsuficienteException.class)
    public ResponseEntity<ExcepcionDTO> creditoInsuficienteExceptionHandler(CreditoInsuficienteException ex) {
        return new ResponseEntity<>(this.createExceptionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ViajeException.class)
    public ResponseEntity<ExcepcionDTO> generarViajeExceptionHandler(ViajeException ex) {
        return new ResponseEntity<>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotValidCodigoQRException.class)
    public ResponseEntity<ExcepcionDTO> notValidCodigoQRExceptionHandler(NotValidCodigoQRException ex) {
        return new ResponseEntity<ExcepcionDTO>(new ExcepcionDTO(ex.getUserMessage(), ex.getSeverity()), HttpStatus.BAD_REQUEST);
    }

    private ExcepcionDTO createExceptionDTO(String message, String severity) {
        return new ExcepcionDTO(message, severity);
    }
}
