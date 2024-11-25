package org.arqui.grupo9.microserviciocuentas.controllers;

import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.services.UsuarioService;
import jakarta.validation.Valid;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioRequestDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioResponseDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.CuentaMPConverter;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.UsuarioResponseConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private UsuarioResponseConverter converter;

    public UsuarioController(UsuarioService usuarioService, @Lazy UsuarioResponseConverter converter) {
        this.usuarioService = usuarioService;
        this.converter = converter;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<Usuario> usuarios = this.usuarioService.findAll();
        if(usuarios.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.converter.fromEntity(usuarios), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long idUsuario) {
        Usuario u = this.usuarioService.findById(idUsuario);
        return new ResponseEntity<>(this.converter.fromEntity(u), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody @Valid UsuarioRequestDTO u) {
        return new ResponseEntity<>(this.usuarioService.save(u), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long idUsuario) {
        return new ResponseEntity<>(this.usuarioService.deleteById(idUsuario), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long idUsuario, @RequestBody @Valid UsuarioRequestDTO uModified) {
        return new ResponseEntity<>(this.usuarioService.updateById(idUsuario, uModified), HttpStatus.OK);
    }
}
