package org.arqui.grupo9.microserviciocuentas.services.dtos.converters;

import org.arqui.grupo9.microserviciocuentas.models.CuentaSistema;
import org.arqui.grupo9.microserviciocuentas.services.dtos.CuentaSistemaResponseDTO;

public class CuentaSistemaConverterResponse extends ConverterDTO<CuentaSistema, CuentaSistemaResponseDTO>{

    @Override
    public CuentaSistemaResponseDTO fromEntity(CuentaSistema e) {
        if(e == null)
            return null;

        return new CuentaSistemaResponseDTO(e.getUsername(), e.getPassword(), e.getUsuarios(), e.getCuentaMp());
    }

    @Override
    public CuentaSistema fromDTO(CuentaSistemaResponseDTO d) {
        return null;
    }
}
