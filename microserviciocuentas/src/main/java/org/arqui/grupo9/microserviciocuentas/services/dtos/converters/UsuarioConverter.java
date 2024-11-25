package org.arqui.grupo9.microserviciocuentas.services.dtos.converters;

import org.arqui.grupo9.microserviciocuentas.models.Usuario;
import org.arqui.grupo9.microserviciocuentas.services.dtos.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter extends ConverterDTO<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO fromEntity(Usuario e) {
        if(e == null)
            return null;

        return new UsuarioDTO(e.getIdUsuario(), e.getNombre(), e.getApellido(), e.getNroCelular(), e.getEmail(), e.getPassword());
    }

    @Override
    public Usuario fromDTO(UsuarioDTO d) {
        if(d == null)
            return null;

        return new Usuario(d.getId(), d.getNombre(), d.getApellido(), d.getNroCelular(), d.getEmail(), d.getPassword());
    }
}
