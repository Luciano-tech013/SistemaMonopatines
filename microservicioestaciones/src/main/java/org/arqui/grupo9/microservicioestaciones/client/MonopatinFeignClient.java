package org.arqui.grupo9.microservicioestaciones.client;

import jakarta.validation.constraints.NotEmpty;
import org.arqui.grupo9.microservicioestaciones.model.dtos.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microserviciomonopatines")
public interface MonopatinFeignClient {

    @GetMapping("/api/monopatines/ubicacion")
    ResponseEntity<List<MonopatinDTO>> getMonopatinesByUbicacion(@RequestParam(name = "latitud", required = true) Double latitud,
                                                                 @RequestParam(name = "longitud", required = true) Double longitud);
}
