package org.arqui.grupo9.microservicioviajes.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.arqui.grupo9.microservicioviajes.model.Viaje;
import org.arqui.grupo9.microservicioviajes.services.ViajeService;
import org.arqui.grupo9.microservicioviajes.services.dtos.MonopatinDTO;
import org.arqui.grupo9.microservicioviajes.services.dtos.ReporteTiempoTotalPausadoDTO;
import org.arqui.grupo9.microservicioviajes.services.dtos.ViajeDTO;
import org.arqui.grupo9.microservicioviajes.services.dtos.converter.ViajeConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeController {
    private ViajeService service;
    private ViajeConverter converter;

    public ViajeController(ViajeService service, @Lazy ViajeConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping()
    public ResponseEntity<List<ViajeDTO>> findAll() {
        List<Viaje> viajes = this.service.findAll();
        if(viajes.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converter.fromEntity(viajes), HttpStatus.OK);
    }

    @GetMapping("/{idViaje}")
    public ResponseEntity<ViajeDTO> findById(@PathVariable Long idViaje) {
        Viaje viajeFinded = this.service.findById(idViaje);
        return new ResponseEntity<>(this.converter.fromEntity(viajeFinded), HttpStatus.FOUND);
    }

    @DeleteMapping("/{idViaje}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idViaje) {
        return new ResponseEntity<>(this.service.deleteById(idViaje), HttpStatus.OK);
    }

    @PostMapping("/generar/cuenta/{idCuentaSistema}/monopatin/{idMonopatin}")
    public ResponseEntity<Boolean> realizarViaje(@PathVariable Long idCuentaSistema, @PathVariable Long idMonopatin) {
        return new ResponseEntity<>(this.service.realizar(idCuentaSistema, idMonopatin), HttpStatus.OK);
    }

    @PutMapping("/viaje/finalizar")
    public ResponseEntity<ViajeDTO> finalizarViaje() {
        return new ResponseEntity<>(this.converter.fromEntity(this.service.finalizar()), HttpStatus.OK);
    }

    @PutMapping("/viaje/pausar")
    public ResponseEntity<ViajeDTO> pausarViaje() {
        return new ResponseEntity<>(this.converter.fromEntity(this.service.pausar()), HttpStatus.OK);
    }

    @PutMapping("/viaje/despausar")
    public ResponseEntity<ViajeDTO> despausarViaje() {
        return new ResponseEntity<>(this.converter.fromEntity(this.service.despausar()), HttpStatus.OK);
    }

    @GetMapping("/monopatines")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesByCantViajesInoneYear(
            @RequestParam(name = "anio", required = true) @Positive(message = "El anio no puede ser negatvivo") int anio,
            @RequestParam(name = "cant_viajes", required = true)  @Positive(message = "El anio no puede ser negativo") int cantViajes) {

        List<MonopatinDTO> monopatines = this.service.getMonopatinesByCantViajesInOneYear(anio, cantViajes);
        if(monopatines.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(monopatines, HttpStatus.OK);
    }

    @GetMapping("/facturacion")
    public ResponseEntity<Double> getTotalFacturadoByMesesInOneYear(
            @RequestParam(name = "desde", required = true) @Positive(message = "El mes de inicio no puede ser negativo") int mesIni,
            @RequestParam(name = "hasta", required = true) @Positive(message = "El mes de fin no puede ser negativo") int mesFin,
            @RequestParam(name = "anio", required = true) @Positive(message = "El anio no puede ser negativo") int anio) {

        return new ResponseEntity<>(this.service.getTotalFacturadoByMesesInOneYear(mesIni, mesFin, anio), HttpStatus.OK);
    }

    @PutMapping("/ajustes/precio")
    public ResponseEntity<Boolean> ajustarPrecioParaUnaFecha(
            @RequestParam(name = "fecha", required = true) LocalDate aPartirDe,
            @RequestParam(name = "precio", required = true) @Positive(message = "El precio no puede ser negativo") double precio) {

        return new ResponseEntity<>(this.service.ajustarPrecioParaUnaFecha(aPartirDe, precio), HttpStatus.OK);
    }

    @GetMapping("/monopatin/{idMonopatin}/reporte/tiempopausado")
    public ResponseEntity<ReporteTiempoTotalPausadoDTO> getTiempoTotalPausadoDeMonopatin(@PathVariable Long idMonopatin) {
        return new ResponseEntity<>(this.service.getTiempoTotalPausadoDeMonopatin(idMonopatin), HttpStatus.OK);
    }
}
