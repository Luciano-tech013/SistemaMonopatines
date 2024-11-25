package org.arqui.grupo9.microservicioestaciones.model.dtos.converter;

import org.arqui.grupo9.microservicioestaciones.model.Estacion;
import org.arqui.grupo9.microservicioestaciones.model.dtos.EstacionDTO;
import org.springframework.stereotype.Component;

@Component
public class EstacionConverter extends ConverterDTO<Estacion, EstacionDTO> {

    public EstacionDTO fromEntity(Estacion e) {
        if (e == null) {
            return null;
        }

        return new EstacionDTO(e.get_id(), e.getNombre(), e.getLatitud(), e.getLongitud());
    }

    public Estacion fromDTO(EstacionDTO d) {
        if(d == null)
            return null;

        return new Estacion(d.getIdEstacion(), d.getNombre(), d.getLatitud(), d.getLongitud());
    }
}
