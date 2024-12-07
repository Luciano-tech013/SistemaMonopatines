package org.arqui.grupo9.serviciomercadopago.service;

import org.arqui.grupo9.serviciomercadopago.model.CuentaMP;
import org.arqui.grupo9.serviciomercadopago.repository.ICuentaMPRepository;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.DeleteCuentaMPException;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.NotFoundCuentaMPException;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.NotFoundUsuarioException;
import org.arqui.grupo9.serviciomercadopago.service.exceptions.UsuarioYaRegistradoException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaMpService {
    private ICuentaMPRepository cuentaMpRepository;

    public CuentaMpService(ICuentaMPRepository cuentaMpRepository) {
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
        System.out.println(cuentaMP.toString());
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
        if(cuenta.tieneUsuariosAsociados())
            throw new DeleteCuentaMPException("Se intento eliminar una cuenta de mercado pago no desvinculada", "Para eliminar una cuenta de mercado pago primero debe desvincularla de los usuarios que la utilizan", "high");

        this.cuentaMpRepository.deleteById(id);
        return true;
    }

    public boolean asociarUsuario(Long idUsuario, Long idCuentaMP) {
        CuentaMP cuenta = this.findById(idCuentaMP);

        if(cuenta.tieneUsuario(idUsuario))
           throw new UsuarioYaRegistradoException("El usuario ya se encuentra asociada a la cuenta de mercado pago", "Ya estas asociado a esta cuenta de mercado pago", "high");

        cuenta.asociarUsuario(idUsuario);
        return cuenta.tieneUsuario(idUsuario);
    }

    public boolean desvincularUsuario(Long idUsuario, Long idCuentaMP) {
        CuentaMP cuenta = this.findById(idCuentaMP);

        if(!cuenta.tieneUsuario(idUsuario))
            throw new NotFoundUsuarioException("El usuario no forma parte de esta cuenta", "No te encuentras vinculado a esta cuenta de mercado pago", "high");

        cuenta.desvincularUsuario(idUsuario);
        return !cuenta.tieneUsuario(idUsuario);
    }
}
