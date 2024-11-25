package org.arqui.grupo9.microserviciocuentas.services.dtos.converters;

import org.arqui.grupo9.microserviciocuentas.models.CuentaMP;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaMpDTO;
import org.springframework.stereotype.Component;

@Component
public class CuentaMPConverter extends ConverterDTO<CuentaMP, CuentaMpDTO> {
    @Override
    public CuentaMpDTO fromEntity(CuentaMP e) {
        if(e == null)
            return null;

        return new CuentaMpDTO(e.getIdCuentaMP(), e.getSaldo());
    }

    @Override
    public CuentaMP fromDTO(CuentaMpDTO d) {
        if(d == null)
            return null;

        return new CuentaMP(d.getIdCuentaMP(), d.getSaldo());
    }
}
