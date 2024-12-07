package org.arqui.grupo9.serviciomercadopago.service.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ConverterDTO<Entity, DTO> {
    public abstract DTO fromEntity(Entity e);
    public abstract Entity fromDTO(DTO d);

    public List<DTO> fromEntity(List<Entity> entities) {
        return entities.stream().map(this::fromEntity).collect(Collectors.toList());
    }

    public List<Entity> fromDTO(List<DTO> dtos) {
        return dtos.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
