package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.clients.CuentaMpClient;
import org.arqui.grupo9.microserviciocuentas.models.Roles;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.IUsuarioRepository;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaMpDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.CuentaMPYaRegistradaException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.DeleteUsuarioException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundCuentaMPException;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.NotFoundUsuarioException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private IUsuarioRepository repository;
    private RolService rolService;
    private CuentaMpClient cuentaMpClient;

    public UsuarioService(IUsuarioRepository repository, @Lazy RolService rolService, @Lazy CuentaMpClient cuentaMpClient) {
        this.repository = repository;
        this.rolService = rolService;
        this.cuentaMpClient = cuentaMpClient;
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

    public boolean save(Usuario u) {
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

    public boolean updateById(Long id, UsuarioDTO usuarioModified) {
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

    public boolean desvincularCuentaMercadoPago(Long idUsuario, Long idCuentaMp) {
        Usuario u = this.findById(idUsuario);
        CuentaMpDTO cuenta = null;
        try {
            cuenta = this.cuentaMpClient.findById(idCuentaMp).getBody();
        } catch (Exception e) {
            throw new NotFoundCuentaMPException("No se encontro la cuenta de mercado pago", "No se encontro la cuenta de mercado pago", "low");
        }

        if(!u.tieneCuenta(cuenta.getIdCuentaMP()))
            throw new CuentaMPYaRegistradaException("Se intento desvincular una cuenta que no esta asociada al usuario", "La cuenta no esta asociada al usuario", "low");

        u.desvincularCuenta(cuenta.getIdCuentaMP());
        cuenta.desvincularUsuario(u.getIdUsuario());
        this.repository.save(u);

        try {
            this.cuentaMpClient.save(cuenta);
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public boolean vincularNuevaCuentaMercadoPago(Long idUsuario, Long idCuentaMp) {
        Usuario u = this.findById(idUsuario);
        CuentaMpDTO cuenta = null;
        try {
            cuenta = this.cuentaMpClient.findById(idCuentaMp).getBody();
        } catch (Exception e) {
            throw new NotFoundCuentaMPException("No se encontro la cuenta de mercado pago", "No se encontro la cuenta de mercado pago", "low");
        }


        if(u.tieneCuenta(cuenta.getIdCuentaMP()))
            throw new CuentaMPYaRegistradaException("Se intento desvincular una cuenta que no esta asociada al usuario", "La cuenta no esta asociada al usuario", "low");

        u.vincularCuenta(cuenta.getIdCuentaMP());
        cuenta.vincularUsuario(u.getIdUsuario());
        this.repository.save(u);

        try {
            this.cuentaMpClient.save(cuenta);
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    public boolean asignarNuevoRol(Long idUsuario, String name) {
        Usuario u = this.findById(idUsuario);
        Roles rol = this.rolService.findByName(name);

        if(u.tieneRol(rol))
            return false;

        u.asignarRol(rol);
        this.repository.save(u);
        return true;
    }

    public boolean desasignarRol(Long idUsuario, String name) {
        Usuario u = this.findById(idUsuario);
        Roles rol = this.rolService.findByName(name);

        if(!u.tieneRol(rol))
            return false;

        u.asignarRol(rol);
        this.repository.save(u);
        return true;
    }
}
