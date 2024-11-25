package org.arqui.grupo9.microservicioviajes.clients;

import jakarta.validation.Valid;
import org.arqui.grupo9.microservicioviajes.services.dtos.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microserviciomonopatines")
public interface MonopatinFeignClient {

    @GetMapping("/api/monopatines/{idMonopatin}")
    ResponseEntity<MonopatinDTO> findById(@PathVariable Long idMonopatin);

    @PostMapping("/api/monopatines")
    ResponseEntity<Boolean> save(@RequestBody @Valid MonopatinDTO monopatin);
}
