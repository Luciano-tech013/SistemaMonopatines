package org.arqui.grupo9.microserviciocuentas.controllers;

import org.arqui.grupo9.microserviciocuentas.models.Roles;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.services.CuentaMpService;
import org.arqui.grupo9.microserviciocuentas.services.UsuarioService;
import jakarta.validation.Valid;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioWithAuthoritiesDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.CuentaMPConverter;
import org.arqui.grupo9.microserviciocuentas.services.dtos.converters.UsuarioConverter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaMpDTO;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    private CuentaMpService cuentaService;
    private UsuarioConverter converter;
    private CuentaMPConverter cuentaConverter;

    public UsuarioController(UsuarioService usuarioService, @Lazy CuentaMpService cuentaService, @Lazy UsuarioConverter converter, @Lazy CuentaMPConverter cuentaConverter) {
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
        this.converter = converter;
        this.cuentaConverter = cuentaConverter;
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

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioWithAuthoritiesDTO> findByEmail(@PathVariable String email) {
        UsuarioWithAuthoritiesDTO u = this.usuarioService.findByEmail(email);
        return new ResponseEntity<>(u, HttpStatus.OK);
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
        return new ResponseEntity<>(this.usuarioService.updateById(idUsuario, this.converter.fromDTO(uModified)), HttpStatus.OK);
    }

    @PutMapping("/{idUsuario}/cuentas/{idCuentaMP}/asociar")
    public ResponseEntity<Boolean> asociarCuentaAUsuario(@PathVariable Long idUsuario, @PathVariable Long idCuentaMP) {
        boolean asociated = this.usuarioService.asociarCuenta(idUsuario, idCuentaMP);

        if(asociated)
            return new ResponseEntity<>(asociated, HttpStatus.OK);

        return new ResponseEntity<>(asociated, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{idUsuario}/cuentas/{idCuentaMP}/eliminar")
    public ResponseEntity<Boolean> eliminarCuentaDeUsuario(@PathVariable Long idUsuario, @PathVariable Long idCuentaMP) {
        //Interprete que el usuario puede desligarse de una cuenta inhabilitada, no hay que chekear
        return new ResponseEntity<>(this.usuarioService.eliminarCuentaDeUsuario(idUsuario, idCuentaMP), HttpStatus.OK);
    }

    @GetMapping("/{idUsuario}/cuentas")
    public ResponseEntity<List<CuentaMpDTO>> getCuentasOfUsuario(@PathVariable Long idUsuario) {
        Set<CuentaMP> cuentas = this.usuarioService.findById(idUsuario).getCuentasMp();
        List<CuentaMP> list = new ArrayList<>(cuentas);

        if(list.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(this.cuentaConverter.fromEntity(list), HttpStatus.OK);
    }
}
