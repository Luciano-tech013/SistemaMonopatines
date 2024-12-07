package org.arqui.grupo9.microserviciocuentas.services.dtos.converters;

import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaDTO;
import org.springframework.stereotype.Component;

@Component
public class CuentaSistemaConverter extends ConverterDTO<CuentaSistema, CuentaSistemaDTO>{

    @Override
    public CuentaSistemaDTO fromEntity(CuentaSistema e) {
        if(e == null)
            return null;

        return new CuentaSistemaDTO(e.getUsername(), e.getPassword(), e.getUsuario().getIdUsuario(), e.getIdCuentaMP());
    }

    @Override
    public CuentaSistema fromDTO(CuentaSistemaDTO d) {
        return null;
    }
}
