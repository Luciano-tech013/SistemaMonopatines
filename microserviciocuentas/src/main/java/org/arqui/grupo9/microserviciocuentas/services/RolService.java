package org.arqui.grupo9.microserviciocuentas.services;

import java.util.List;
import java.util.Optional;

import org.arqui.grupo9.microserviciocuentas.models.Roles;
import org.arqui.grupo9.microserviciocuentas.repositories.IRolRepository;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundRolException;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private IRolRepository rolRepository;

    public RolService(IRolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Roles> findAll() {
        return this.rolRepository.findAll();
    }

    public Roles save(Roles rol) {
        return this.rolRepository.save(rol);
    }

    public Roles findByName(String name) {
        Optional<Roles> rol = this.rolRepository.findById(name);
        if(rol.isPresent())
            return rol.get();

        throw new NotFoundRolException("El rol solicitado no esta cargado en el sistema", "El rol solicitado no esta registrado en el sistema", "low");
    }
}
