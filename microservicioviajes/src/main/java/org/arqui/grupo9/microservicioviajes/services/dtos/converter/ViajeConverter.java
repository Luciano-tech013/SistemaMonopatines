package org.arqui.grupo9.microservicioviajes.services.dtos.converter;

import org.arqui.grupo9.microservicioviajes.model.Viaje;
import org.arqui.grupo9.microservicioviajes.services.dtos.ViajeDTO;
import org.springframework.stereotype.Component;

@Component
public class ViajeConverter extends ConverterDTO<Viaje, ViajeDTO> {

    @Override
    public ViajeDTO fromEntity(Viaje e) {
        if(e == null)
            return null;

        return new ViajeDTO(e.getFechaIniViaje(), e.getFechaFinViaje(), e.getKmsRecorridos(), e.getCostoTotal());
    }

    @Override
    public Viaje fromDTO(ViajeDTO d) {
        return null;
    }
}
