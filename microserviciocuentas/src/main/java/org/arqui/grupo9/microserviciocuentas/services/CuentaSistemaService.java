package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.clients.CuentaMpClient;
import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.ICuentaSistemaRepository;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaMpDTO;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaDTO;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaSistemaService {
    private ICuentaSistemaRepository repository;
    private UsuarioService usuarioService;
    private CuentaMpClient cuentaMpClient;

    public CuentaSistemaService(@Lazy ICuentaSistemaRepository repository, @Lazy UsuarioService usuarioService, @Lazy CuentaMpClient cuentaMpClient) {
        this.repository = repository;
        this.usuarioService = usuarioService;
        this.cuentaMpClient = cuentaMpClient;
    }

    public List<CuentaSistema> findAll() {
        return this.repository.findAll();
    }

    public CuentaSistema findById(Long idCuentaSistema) {
        Optional<CuentaSistema> cuentaSistema = this.repository.findById(idCuentaSistema);
        if(cuentaSistema.isPresent())
            return cuentaSistema.get();

        throw new NotFoundCuentaSistemaException("No existe la cuenta que solicito el cliente", "La cuenta solicitada no existe. Por favor verifica que este cargada en el sistema", "low");
    }

    public boolean crearCuenta(CuentaSistemaDTO cuenta) {
        Usuario u = this.usuarioService.findById(cuenta.getIdUsuario());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(cuenta.getPassword());
        cuenta.setPassword(encodePassword);

        CuentaSistema cuentaSistema = new CuentaSistema(cuenta.getUsername(), cuenta.getPassword(), u, cuenta.getIdCuenta());

        this.repository.save(cuentaSistema);
        return true;
    }

    public boolean eliminarCuenta(Long idCuentaSistema) {
        this.findById(idCuentaSistema);
        this.repository.deleteById(idCuentaSistema);
        return true;
    }

    public boolean updateById(Long idCuentaSistema, CuentaSistemaDTO cuenta) {
        Usuario u = this.usuarioService.findById(cuenta.getIdUsuario());

        CuentaSistema c = this.findById(idCuentaSistema);

        c.setUsername(cuenta.getUsername());
        c.setPassword(cuenta.getPassword());
        c.setUsuario(u);
        c.setIdCuentaMP(cuenta.getIdCuenta());

        this.repository.save(c);
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
        if(cuenta.getFechaInahilitada() == null)
            return true;

        if(cuenta.getFechaInahilitada().isEqual(LocalDate.now()) || cuenta.getFechaInahilitada().isBefore(LocalDate.now())) {
            cuenta.setInhabilitada(false);
            cuenta.setFechaInahilitada(null);
            return true;
        }

        return false;
    }
}
