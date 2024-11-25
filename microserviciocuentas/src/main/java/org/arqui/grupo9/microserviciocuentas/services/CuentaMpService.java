package org.arqui.grupo9.microserviciocuentas.services;

import org.arqui.grupo9.microserviciocuentas.repositories.ICuentaMpRepository;
import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.services.exceptions.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaMpService {

    private ICuentaMpRepository cuentaMpRepository;

    public CuentaMpService(ICuentaMpRepository cuentaMpRepository) {
        this.cuentaMpRepository = cuentaMpRepository;
    }

    public List<CuentaMP> findAllCuentas() {
        return this.cuentaMpRepository.findAll();
    }

    public CuentaMP findById(Long id) {
        Optional<CuentaMP> cuenta = this.cuentaMpRepository.findById(id);
        if(cuenta.isPresent())
            return cuenta.get();

        throw new NotFoundCuentaMPException("No existe la cuenta que solicito el cliente", "La cuenta solicitada no existe. Por favor verifica que este cargada en el sistema", "low");
    }

    public boolean save(CuentaMP cuentaMP) {
        this.cuentaMpRepository.save(cuentaMP);
        return true;
    }

    public boolean cargarSaldo(Long id, double saldo) {
        CuentaMP cuenta = this.findById(id);
        cuenta.setSaldo(cuenta.getSaldo() + saldo);
        return this.save(cuenta);
    }

    public boolean descontarSaldo(Long id, double saldo) {
        CuentaMP cuenta = this.findById(id);
        //NO ES NECESARIO CHEKEAR SI EL SALDO QUDA NEGATIVO, YA QUE NO PODRA SEGUIR REALIZANDO VIAJES
        cuenta.setSaldo(cuenta.getSaldo() - saldo);
        return this.save(cuenta);
    }

    public boolean deleteById(Long id) {
        CuentaMP cuenta = this.findById(id);

        //PARA MANTENER CONSISTENCIA Y EVITAR EXCEPCION DE LA BASE
        if(cuenta.tieneCuentasSistema())
            throw new DeleteCuentaMPException("Se intento eliminar una cuenta de mercado pago no desvinculada", "Para eliminar una cuenta de mercado pago primero debe desvincularla de las cuentas que la utilizan", "high");

        this.cuentaMpRepository.deleteById(id);
        return true;
    }
}
