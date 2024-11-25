package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.ICuentaSistemaRepository;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaRequestDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaSistemaService {
    private ICuentaSistemaRepository repository;
    private UsuarioService usuarioService;
    private CuentaMpService cuentaMpService;

    public CuentaSistemaService(ICuentaSistemaRepository repository, @Lazy UsuarioService usuarioService, @Lazy CuentaMpService cuentaMpService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.cuentaMpService = cuentaMpService;
    }

    public List<CuentaSistema> findAll() {
        return this.repository.findAll();
    }

    public CuentaSistema findById(Long idCuentaSistema) {
        Optional<CuentaSistema> cuentaSistema = this.repository.findById(idCuentaSistema);
        if(cuentaSistema.isPresent())
            return cuentaSistema.get();

        throw new NotFoundCuentaMPException("No existe la cuenta que solicito el cliente", "La cuenta solicitada no existe. Por favor verifica que este cargada en el sistema", "low");
    }

    public boolean crearCuenta(CuentaSistemaRequestDTO cuenta) {
        Usuario usuario = this.usuarioService.findById(cuenta.getIdUsuario());
        CuentaMP cuentaMp = this.cuentaMpService.findById(cuenta.getIdCuentaMp());

        CuentaSistema cuentaSistema = new CuentaSistema(cuenta.getUsername(), cuenta.getPassword(), cuentaMp);
        cuentaSistema.asignarUsuario(usuario);

        this.repository.save(cuentaSistema);
        return true;
    }

    public boolean deleteById(Long idCuentaSistema) {
        this.findById(idCuentaSistema);
        this.repository.deleteById(idCuentaSistema);
        return true;
    }

    public boolean updateById(Long idCuentaSistema, CuentaSistemaRequestDTO cuenta) {
        CuentaSistema c = this.findById(idCuentaSistema);

        c.setUsername(cuenta.getUsername());
        c.setPassword(cuenta.getPassword());

        this.repository.save(c);
        return true;
    }

    public boolean asignarNuevoUsuario(Long idUsuario, Long idCuentaSistema) {
        Usuario u = this.usuarioService.findById(idUsuario);
        CuentaSistema c = this.findById(idCuentaSistema);

        if(!this.estaHabilitada(c))
            throw new CuentaInhabilitadaException("La cuenta en la que se intento operar esta inhabilitada", "La cuenta se encuentra inhabilitada. No se puede usar", "high");

        if(c.tieneUsuario(u))
            throw new UsuarioYaRegistradoException("Se intento registrar un usuario ya registrado en esa cuenta", "El usuario ya esta vinculado en esta cuenta", "low");

        c.asignarUsuario(u);
        u.asociarCuenta(c);
        this.repository.save(c);
        this.usuarioService.saveUsuario(u);
        return true;
    }

    public boolean desvincularUsuario(Long idUsuario, Long idCuentaSistema) {
        Usuario u = this.usuarioService.findById(idUsuario);
        CuentaSistema c = this.findById(idCuentaSistema);

        if(!this.estaHabilitada(c))
            throw new CuentaInhabilitadaException("La cuenta en la que se intento operar esta inhabilitada", "La cuenta se encuentra inhabilitada. No se puede usar", "high");

        if(!c.tieneUsuario(u))
            throw new NotFoundUsuarioException("El usuario que se intento desvincular no esta registrado en la cuenta", "El usuario enviado no esta registrado en esta cuenta", "low");

        c.desasignarUsuario(u);
        u.eliminarCuenta(c);
        this.repository.save(c);
        this.usuarioService.saveUsuario(u);
        return true;
    }

    public boolean desvincularCuentaMercadoPago(Long idCuentaSistema, Long idCuentaMp) {
        CuentaSistema c = this.findById(idCuentaSistema);
        CuentaMP cMp = this.cuentaMpService.findById(idCuentaMp);

        if(!this.estaHabilitada(c))
            throw new CuentaInhabilitadaException("La cuenta en la que se intento operar esta inhabilitada", "La cuenta se encuentra inhabilitada. No se puede usar", "high");

        if(!c.tieneCuentaMercadoPago(cMp))
            throw new NotFoundCuentaMPException("La cuenta de mercado pago enviada no esta vinculada a la cuenta solicitada", "Se detecto que la cuenta de mercado pago enviada no esta vinculada a esta cuenta", "low");

        c.desvincularCuentaMercadoPago();
        cMp.desvincularCuentaSistema(c);
        this.repository.save(c);
        this.cuentaMpService.save(cMp);
        return true;
    }

    public boolean vincularNuevaCuentaMercadoPago(Long idCuentaSistema, Long idCuentaMp) {
        CuentaSistema c = this.findById(idCuentaSistema);
        CuentaMP cMp = this.cuentaMpService.findById(idCuentaMp);

        if(!this.estaHabilitada(c))
            throw new CuentaInhabilitadaException("La cuenta en la que se intento operar esta inhabilitada", "La cuenta se encuentra inhabilitada. No se puede usar", "high");

        if(c.tieneCuentaMercadoPago(cMp))
            throw new CuentaMPYaRegistradaException("La cuenta de mercado pago ya se encuentra vinculada en esa cuenta", "La cuenta de mercado pago solicitada ya se encuentra vinculada a esta cuenta", "low");

        c.asignarNuevaCuentaMercadoPago(cMp);
        cMp.asociarCuentaSistema(c);
        this.repository.save(c);
        this.cuentaMpService.save(cMp);
        return true;
    }

    public boolean inhabilitarCuenta(Long idCuentaSistema, LocalDate fechaHasta) {
        if(!fechaHasta.isAfter(LocalDate.now()))
            throw new NotFoundFechaException("La fecha ingresada es menor a hoy", "Debe ingresar una fecha tope mayor a hoy", "high");

        CuentaSistema cuenta = this.findById(idCuentaSistema);

        if(!this.estaHabilitada(cuenta))
            throw new CuentaInhabilitadaException("Se intento inhabilitar una cuenta ya inhabilitada", "La cuenta ya se se encuentra inhabilitada", "high");

        if(!this.estaHabilitada(cuenta))
            throw new CuentaInhabilitadaException("La cuenta no esta habiltiada", "La cuenta que intentas asociar no se encuentra habilitada", "high");

        cuenta.setInhabilitada(true);
        cuenta.setFechaInahilitada(fechaHasta);
        this.repository.save(cuenta);
        return true;
    }

    public boolean estaHabilitada(CuentaSistema cuenta) {
        if(cuenta.getFechaInahilitada().isEqual(LocalDate.now()) || cuenta.getFechaInahilitada().isBefore(LocalDate.now())) {
            cuenta.setInhabilitada(false);
            cuenta.setFechaInahilitada(null);
            return true;
        }

        return false;
    }
}
