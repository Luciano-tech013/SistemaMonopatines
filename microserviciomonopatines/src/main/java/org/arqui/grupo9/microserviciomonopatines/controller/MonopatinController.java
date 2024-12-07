package org.arqui.grupo9.microserviciomonopatines.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.arqui.grupo9.microserviciomonopatines.model.Monopatin;
import org.arqui.grupo9.microserviciomonopatines.service.MonopatinService;
import org.arqui.grupo9.microserviciomonopatines.service.dto.MonopatinDTO;
import org.arqui.grupo9.microserviciomonopatines.service.dto.ReporteEstadoDTO;
import org.arqui.grupo9.microserviciomonopatines.service.dto.ReporteUsoDTO;
import org.arqui.grupo9.microserviciomonopatines.service.dto.converter.MonopatinConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monopatines")
public class MonopatinController {

    private MonopatinService monopatinService;
    private MonopatinConverter converter;

    public MonopatinController(MonopatinService monopatinService, @Lazy MonopatinConverter converter) {
        this.monopatinService = monopatinService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<MonopatinDTO>> findAll() {
        List<Monopatin> monopatines = this.monopatinService.findAll();
        if(monopatines.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converter.fromEntity(monopatines), HttpStatus.OK);
    }

    @GetMapping("/{idMonopatin}")
    public ResponseEntity<MonopatinDTO> findById(@PathVariable Long idMonopatin) {
        Monopatin monopatin = this.monopatinService.findById(idMonopatin);
        return ResponseEntity.ok().header("Content-Type", "application/json").body(converter.fromEntity(monopatin));
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody @Valid MonopatinDTO monopatin) {
        return new ResponseEntity<>(this.monopatinService.save(this.converter.fromDTO(monopatin)), HttpStatus.OK);
    }

    @DeleteMapping("/{idMonopatin}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idMonopatin) {
        return new ResponseEntity<>(this.monopatinService.deleteByid(idMonopatin), HttpStatus.OK);
    }

    @PutMapping("/{idMonopatin}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long idMonopatin, @RequestBody MonopatinDTO monopatin) {
        return new ResponseEntity<>(this.monopatinService.updateById(idMonopatin, this.converter.fromDTO(monopatin)), HttpStatus.OK);
    }

    @GetMapping("/reporte/estado")
    public ResponseEntity<ReporteEstadoDTO> generarReporteEstado() {
        List<Monopatin> monopatinesEnOperacion = this.monopatinService.getMonopatinesByEstado("operacion");
        List<Monopatin> monopatinesEnMantenimiento = this.monopatinService.getMonopatinesByEstado("mantenimiento");

        ReporteEstadoDTO reporte = new ReporteEstadoDTO((monopatinesEnMantenimiento.isEmpty()) ? 0 : monopatinesEnMantenimiento.size(),
                (monopatinesEnOperacion.isEmpty()) ? 0 : monopatinesEnOperacion.size());
        return new ResponseEntity<>(reporte, HttpStatus.OK);
    }

    @GetMapping("/ubicacion")
    public ResponseEntity<List<MonopatinDTO>> getMonopatinesByUbicacion(@RequestParam(name = "latitud", required = true) Double latitud,
                                                                        @RequestParam(name = "longitud", required = true) Double longitud) {
        List<Monopatin> monopatines = this.monopatinService.getMonopatinesByUbicacion(latitud, longitud);
        return new ResponseEntity<>(this.converter.fromEntity(monopatines), HttpStatus.OK);
    }

    @GetMapping("/reporte/uso")
    public ResponseEntity<List<ReporteUsoDTO>> generarReporteByUso(@RequestParam(name = "conPausa", required = true) boolean conPausa) {
        List<ReporteUsoDTO> reportes = this.monopatinService.getMonopatinesByUso(conPausa);

        if(reportes == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.monopatinService.getMonopatinesByUso(conPausa), HttpStatus.OK);
    }

}
