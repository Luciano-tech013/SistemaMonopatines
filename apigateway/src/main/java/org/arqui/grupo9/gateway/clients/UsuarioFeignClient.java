package org.arqui.grupo9.gateway.clients;

import org.arqui.grupo9.gateway.service.dtos.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microserviciocuentas")
public interface UsuarioFeignClient {

    @GetMapping("/{email}")
    ResponseEntity<UsuarioDTO> findByEmail(@PathVariable String email);
}
