package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.models.Roles;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.IUsuarioRepository;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioRequestDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.DeleteUsuarioException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundUsuarioException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private IUsuarioRepository repository;
    private RolService rolService;

    public UsuarioService(IUsuarioRepository repository, @Lazy RolService rolService) {
        this.repository = repository;
        this.rolService = rolService;
    }

    public List<Usuario> findAll() {
        return this.repository.findAll();
    }

    public Usuario findById(Long id) {
        Optional<Usuario> u = this.repository.findById(id);
        if(u.isPresent())
            return u.get();

        throw new NotFoundUsuarioException("El usuario solicitado no esta cargado en el sistema", "El usuario solicitado no existe. Por favor, verifica que este cargado en el sistema", "low");
    }

    public boolean save(UsuarioRequestDTO uDTO) {
        Usuario usuario = new Usuario(uDTO.getNombre(), uDTO.getApellido(), uDTO.getNroCelular(), uDTO.getEmail());
        Roles rol = this.rolService.findByName("USUARIO");
        usuario.asignarRol(rol);
        this.repository.save(usuario);
        return true;
    }

    //SAVE PARA AQUELLOS SERVICIOS INTERNOS QUE NECESITAN PERSISTIR UN USUARIO
    public boolean saveUsuario(Usuario u) {
        this.repository.save(u);
        return true;
    }

    public boolean deleteById(Long id) {
        Usuario u = this.findById(id);

        if(u.tieneCuentasSistema())
            throw new DeleteUsuarioException("Se intento eliminar un usuario que esta asociado a una cuenta", "No se puede eliminar un usuario si esta asociado a una cuenta", "high");

        this.repository.deleteById(id);
        return true;
    }

    public boolean updateById(Long id, UsuarioRequestDTO usuarioModified) {
        Usuario u = this.findById(id);
        if(u == null)
            return false;

        u.setNombre(usuarioModified.getNombre());
        u.setApellido(usuarioModified.getApellido());
        u.setEmail(usuarioModified.getEmail());
        u.setNroCelular(usuarioModified.getNroCelular());

        this.repository.save(u);
        return true;
    }
}
