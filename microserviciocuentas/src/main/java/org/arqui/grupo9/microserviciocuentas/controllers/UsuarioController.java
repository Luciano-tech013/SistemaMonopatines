package org.arqui.grupo9.microserviciocuentas.controllers;

import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.services.UsuarioService;
import jakarta.validation.Valid;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.UsuarioConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioConverter converter;

    public UsuarioController(UsuarioService usuarioService, @Lazy UsuarioConverter converter) {
        this.usuarioService = usuarioService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = this.usuarioService.findAll();
        if(usuarios.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converter.fromEntity(usuarios), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long idUsuario) {
        Usuario u = this.usuarioService.findById(idUsuario);
        return new ResponseEntity<>(this.converter.fromEntity(u), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody @Valid UsuarioDTO u) {
        return new ResponseEntity<>(this.usuarioService.save(this.converter.fromDTO(u)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idUsuario) {
        return new ResponseEntity<>(this.usuarioService.deleteById(idUsuario), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioDTO uModified) {
        return new ResponseEntity<>(this.usuarioService.updateById(idUsuario, uModified), HttpStatus.OK);
    }

    /*EL USUARIO AL SER DUEÑO DE LA RELACION ES QUIEN CONTROLA TMB A CUENTAMp*/
    @PutMapping("/{idUsuario}/mercadopago/desvincular/{idCuentaMP}")
    public ResponseEntity<Boolean> desvincularCuentaMercadoPago(@PathVariable Long idUsuario, @PathVariable Long idCuentaMP) {
        return new ResponseEntity<>(this.usuarioService.desvincularCuentaMercadoPago(idUsuario, idCuentaMP), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/mercadopago/vincular/{idCuentaMP}")
    public ResponseEntity<Boolean> vincularNuevaCuentaMercadoPago(@PathVariable Long idUsuario, @PathVariable Long idCuentaMP) {
        return new ResponseEntity<>(this.usuarioService.vincularNuevaCuentaMercadoPago(idUsuario, idCuentaMP), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/roles/{name}/asignar")
    public ResponseEntity<Boolean> asignarNuevoRol(@PathVariable Long idUsuario, @PathVariable String name) {
        return new ResponseEntity<>(this.usuarioService.asignarNuevoRol(idUsuario, name), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/roles/{name}/desasignar")
    public ResponseEntity<Boolean> desasignarNuevoRol(@PathVariable Long idUsuario, @PathVariable String name) {
        return new ResponseEntity<>(this.usuarioService.desasignarRol(idUsuario, name), HttpStatus.OK);
    }
}
