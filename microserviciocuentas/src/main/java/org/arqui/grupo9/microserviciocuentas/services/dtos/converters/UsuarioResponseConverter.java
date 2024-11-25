package org.arqui.grupo9.microserviciocuentas.services.dtos.converters;

import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponseConverter extends ConverterDTO<Usuario, UsuarioResponseDTO> {

    @Override
    public UsuarioResponseDTO fromEntity(Usuario e) {
        if(e == null)
            return null;

        return new UsuarioResponseDTO(e.getNombre(), e.getApellido(), e.getNroCelular(), e.getEmail(), e.getCuentasSistema());
    }

    @Override
    public Usuario fromDTO(UsuarioResponseDTO d) {
         return null;
    }
}
