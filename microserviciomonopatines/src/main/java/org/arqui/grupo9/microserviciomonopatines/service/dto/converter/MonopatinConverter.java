package org.arqui.grupo9.microserviciomonopatines.service.dto.converter;

import org.arqui.grupo9.microserviciomonopatines.model.Monopatin;
import org.arqui.grupo9.microserviciomonopatines.service.dto.MonopatinDTO;
import org.springframework.stereotype.Component;

@Component
public class MonopatinConverter extends ConverterDTO<Monopatin, MonopatinDTO> {

    @Override
    public MonopatinDTO fromEntity(Monopatin e) {
        if(e == null)
            return null;

        return new MonopatinDTO(e.getIdMonopatin(), e.getKmsRecorridos(), e.getModelo(), e.getLatitud(), e.getLongitud(), e.getEstado(), e.getCodigoQR());
    }

    @Override
    public Monopatin fromDTO(MonopatinDTO d) {
        if(d == null)
            return null;

        return new Monopatin(d.getIdMonopatin(), d.getKmsRecorridos(), d.getModelo(), d.getLatitud(), d.getLongitud(), d.getEstado(), d.getCodigoQR());
    }
}
