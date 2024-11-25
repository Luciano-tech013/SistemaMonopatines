package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.repositories.ICuentaMpRepository;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaMpService {

    private ICuentaMpRepository cuentaMpRepository;
    private UsuarioService usuarioService;

    public CuentaMpService(ICuentaMpRepository cuentaMpRepository, @Lazy UsuarioService service) {
        this.cuentaMpRepository = cuentaMpRepository;
        this.usuarioService = service;
    }

    public List<CuentaMP> findAllCuentas() {
        return this.cuentaMpRepository.findAll();
    }

    public CuentaMP findByIdCuenta(Long id) {
        Optional<CuentaMP> cuenta = this.cuentaMpRepository.findById(id);
        if(cuenta.isPresent())
            return cuenta.get();

        throw new NotFoundCuentaException("No existe la cuenta que solicito el cliente", "La cuenta solicitada no existe. Por favor verifica que este cargada en el sistema", "low");
    }

    public boolean crearCuenta(Long idUsuario) {
        Usuario u = this.usuarioService.findById(idUsuario);

        CuentaMP mp = new CuentaMP(0.00);

        if(mp.tieneUsuario(u))
            throw new UsuarioYaRegistradoException("El usuario intento asociarse a una cuenta a la que ya pertenece", "Ya te encuentras asociado a esta cuenta", "low");

        mp.registrarUsuario(u);
        u.asociarCuenta(mp);

        if(mp.tieneUsuario(u)) {
            this.saveCuenta(mp);
            if(u.tieneCuenta(mp)) {
                this.usuarioService.save(u);
                return true;
            }
        }

        throw new CouldNotRegisterException("No se registro el usuario en la cuenta y no se puedo asociarlo", "No se puedo crear la cuenta. Por favor intenta mas tarde", "high");
    }

    public boolean saveCuenta(CuentaMP cuentaMP) {
        this.cuentaMpRepository.save(cuentaMP);
        return true;
    }

    //Equivale a un Update
    public boolean cargarSaldoCuenta(Long id, double saldo) {
        CuentaMP cuenta = this.findByIdCuenta(id);
        if(this.estaHabilitada(cuenta))
            throw new CuentaInhabilitadaException("La cuenta no esta habiltiada", "La cuenta que intentas asociar no se encuentra habilitada", "high");

        cuenta.setSaldo(cuenta.getSaldo() + saldo);
        return this.saveCuenta(cuenta);
    }

    public boolean descontarSaldoCuenta(Long id, double saldo) {
        CuentaMP cuenta = this.findByIdCuenta(id);
        if(!this.estaHabilitada(cuenta))
            throw new CuentaInhabilitadaException("La cuenta no esta habiltiada", "La cuenta que intentas asociar no se encuentra habilitada", "high");

        cuenta.setSaldo(cuenta.getSaldo() - saldo);
        return this.saveCuenta(cuenta);
    }

    public boolean deleteByIdCuenta(Long id) {
        this.findByIdCuenta(id);
        this.cuentaMpRepository.deleteById(id);
        return true;
    }

    public boolean inhabilitarCuenta(Long idCuentaMP, LocalDate fechaHasta) {
        if(!fechaHasta.isAfter(LocalDate.now()))
            throw new NotFoundFechaException("La fecha ingresada es menor a hoy", "Debe ingresar una fecha tope mayor a hoy", "high");

        CuentaMP cuenta = this.findByIdCuenta(idCuentaMP);

        if(!this.estaHabilitada(cuenta))
            throw new CuentaInhabilitadaException("La cuenta no esta habiltiada", "La cuenta que intentas asociar no se encuentra habilitada", "high");

        cuenta.setInhabilitada(true);
        cuenta.setFechaInahilitada(fechaHasta);
        return this.saveCuenta(cuenta);
    }

    public boolean estaHabilitada(CuentaMP cuentaMP) {
        if(cuentaMP.getFechaInahilitada().isEqual(LocalDate.now()) || cuentaMP.getFechaInahilitada().isBefore(LocalDate.now())) {
            cuentaMP.setInhabilitada(false);
            cuentaMP.setFechaInahilitada(null);
            return true;
        }

        return false;
    }
}
