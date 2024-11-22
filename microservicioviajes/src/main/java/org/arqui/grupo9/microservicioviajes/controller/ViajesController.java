package org.arqui.grupo9.microservicioviajes.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.arqui.grupo9.microservicioviajes.model.dtos.MonopatinDTO;
import org.arqui.grupo9.microservicioviajes.model.dtos.ViajeDTO;
import org.arqui.grupo9.microservicioviajes.services.ViajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajesController {
    private ViajeService service;

    public ViajesController(ViajeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ViajeDTO>> findAll() {
        List<ViajeDTO> viajes = this.service.findAll();
        if(viajes == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(viajes, HttpStatus.OK);
    }

    @GetMapping("/{idViaje}")
    public ResponseEntity<ViajeDTO> findById(@PathVariable Long idViaje) {
        ViajeDTO viajeFinded = this.service.findById(idViaje);
        return new ResponseEntity<>(viajeFinded, HttpStatus.FOUND);
    }

    @DeleteMapping("/{idViaje}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idViaje) {
        return new ResponseEntity<>(this.service.deleteById(idViaje), HttpStatus.OK);
    }

    @PostMapping("/generar/usuario/{idUsuario}/monopatin/{idMonopatin}")
    public ResponseEntity<Boolean> generarViaje(@PathVariable Long idUsuario, @PathVariable Long idMonopatin) {
        System.out.println("Hasta aca llegue!!");
        return new ResponseEntity<>(this.service.generar(idUsuario, idMonopatin), HttpStatus.OK);
    }

    @PutMapping("/finalizar/viaje")
    public ResponseEntity<ViajeDTO> finalizarViaje() {
        return new ResponseEntity<>(this.service.finalizar(), HttpStatus.OK);
    }

    @PutMapping("/pausar/viaje")
    public ResponseEntity<Boolean> pausarViaje() {
        return new ResponseEntity<>(this.service.pausar(), HttpStatus.OK);
    }

    @PutMapping("/despausar/viaje")
    public ResponseEntity<Boolean> despausarViaje() {
        return new ResponseEntity<>(this.service.despausar(), HttpStatus.OK);
    }

    @GetMapping("/monopatines/")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesByCantViajesInoneYear(
            @RequestParam(name = "anio", required = true) @NotEmpty(message = "El anio no puede ser vacio") @Positive(message = "El anio no puede ser negatvivo") int anio,
            @RequestParam(name = "cant_viajes", required = true) @NotEmpty(message = "La cantidad no puede ser negativa") @Positive(message = "El anio no puede ser negativo") int cantViajes) {

        List<MonopatinDTO> monopatines = this.service.getMonopatinesByCantViajesInOneYear(anio, cantViajes);
        if(monopatines.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(monopatines, HttpStatus.OK);
    }

    @GetMapping("/facturacion")
    public ResponseEntity<Double> getTotalFacturadoByMesesInOneYear(
            @RequestParam(name = "desde", required = true) @NotEmpty(message = "La fecha de inicio no puede ser vacia") @Positive(message = "El mes de inicio no puede ser negativo") int mesIni,
            @RequestParam(name = "hasta", required = true) @NotEmpty(message = "La fecha de fin no puede ser vacia") @Positive(message = "El mes de fin no puede ser negativo") int mesFin,
            @RequestParam(name = "anio", required = true) @NotEmpty(message = "El año no puede ser vacio") @Positive(message = "El anio no puede ser negativo") int anio) {

        return new ResponseEntity<>(this.service.getTotalFacturadoByMesesInOneYear(mesIni, mesFin, anio), HttpStatus.OK);
    }

    @PutMapping("/ajustes/precio")
    public ResponseEntity<Boolean> ajustarPrecioParaUnaFecha(
            @RequestParam(name = "fecha", required = true) @NotEmpty(message = "La fecha no es valida") LocalDate aPartirDe,
            @RequestParam(name = "precio", required = true) @NotEmpty(message = "El precio no es valido") @Positive(message = "El precio no puede ser negativo") double precio) {

        return new ResponseEntity<>(this.service.ajustarPrecioParaUnaFecha(aPartirDe, precio), HttpStatus.OK);
    }

    @GetMapping("/monopatin/reporte/tiempopausado/{idMonopatin}")
    public ResponseEntity<Duration> getTiempoTotalPausadoDeMonopatin(@PathVariable Long idMonopatin) {
        return new ResponseEntity<>(this.service.getTiempoTotalPausadoDeMonopatin(idMonopatin), HttpStatus.OK);
    }
}
