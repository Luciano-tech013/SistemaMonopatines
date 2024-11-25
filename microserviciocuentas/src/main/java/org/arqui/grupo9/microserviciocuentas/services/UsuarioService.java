package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.models.Roles;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.IUsuarioRepository;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioWithAuthoritiesDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.CouldNotRegisterException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundCuentaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundUsuarioException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    private IUsuarioRepository repository;
    private CuentaMpService cuentaMpService;
    private RolService rolService;

    public UsuarioService(IUsuarioRepository repository, @Lazy CuentaMpService cuentaMpService, @Lazy RolService rolService) {
        this.repository = repository;
        this.cuentaMpService = cuentaMpService;
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

    public UsuarioWithAuthoritiesDTO findByEmail(String email) {
        Optional<Usuario> u = this.repository.findByEmail(email);
        if(u.isPresent()) {
            Set<Roles> roles = u.get().getRoles();

            List<String> authoritiesName = new LinkedList<>();
            for(Roles rol : roles) {
                authoritiesName.add(rol.getName());
            }

            return new UsuarioWithAuthoritiesDTO(u.get().getEmail(), u.get().getPassword(), authoritiesName);
        }

        throw new NotFoundUsuarioException("El usuario solicitado no esta cargado en el sistema", "El usuario solicitado no existe. Por favor, verifica que este cargado en el sistema", "low");
    }

    public boolean save(Usuario usuario) {
        Roles rol = this.rolService.findByName("USUARIO");
        usuario.asignarRol(rol);
        this.repository.save(usuario);
        return true;
    }

    public boolean deleteById(Long id) {
        System.out.println("Aca si llegue");
        this.findById(id);
        System.out.println("Aca no llegue");
        this.repository.deleteById(id);
        return true;
    }

    public boolean updateById(Long id, Usuario usuarioModified) {
        Usuario u = this.findById(id);
        if(u == null)
            return false;

        u.setNombre(usuarioModified.getNombre());
        u.setApellido(usuarioModified.getApellido());
        u.setEmail(usuarioModified.getEmail());
        u.setNroCelular(usuarioModified.getNroCelular());

        return this.save(u);
    }

    public boolean asociarCuenta(Long idUsuario, Long idCuentaMP) {
        Usuario u = this.findById(idUsuario);
        CuentaMP cuenta = this.cuentaMpService.findByIdCuenta(idCuentaMP);

        if(!cuentaMpService.estaHabilitada(cuenta))
            return false;

        cuenta.registrarUsuario(u);
        u.asociarCuenta(cuenta);
        this.save(u);
        this.cuentaMpService.saveCuenta(cuenta);

        return cuenta.tieneUsuario(u);
    }

    public boolean eliminarCuentaDeUsuario(Long idUsuario, Long idCuentaMP) {
        CuentaMP cuenta = this.cuentaMpService.findByIdCuenta(idCuentaMP);
        Usuario usuario = this.findById(idUsuario);

        if(!usuario.tieneCuenta(cuenta))
            throw new NotFoundCuentaException("La cuenta solicitada para eliminar no esta cargada en el sistema", "La cuenta que intentas eliminar no esta asociada al usuario indicado", "low");

        usuario.eliminarCuenta(cuenta);
        cuenta.eliminarUsuario(usuario);

        if(usuario.tieneCuenta(cuenta))
            throw new CouldNotRegisterException("No se puedo deshacer la cuenta del usuario indicado", "No se puedo eliminar la cuenta del usuario indicado. Por favor intenta mas tarde", "high");

        this.cuentaMpService.saveCuenta(cuenta);
        return this.save(usuario);
    }

}
