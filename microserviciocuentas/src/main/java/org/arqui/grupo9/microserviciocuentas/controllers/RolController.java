package org.arqui.grupo9.microserviciocuentas.controllers;

import org.arqui.grupo9.microserviciocuentas.models.Roles;
import org.arqui.grupo9.microserviciocuentas.services.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    private RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Roles>> findAll() {
        List<Roles> authorities = this.rolService.findAll();
        if(authorities.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Roles> save(@RequestBody Roles rol) {
        rol.setName(rol.getName().toUpperCase());
        return new ResponseEntity<>(this.rolService.save(rol), HttpStatus.CREATED);
    }
}
