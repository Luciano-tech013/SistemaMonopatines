package org.arqui.grupo9.gateway.security;

import org.arqui.grupo9.gateway.clients.UsuarioFeignClient;
import org.arqui.grupo9.gateway.service.dtos.UsuarioDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainUserDetailsService implements UserDetailsService {
    private UsuarioFeignClient usuarioClient;

    public DomainUserDetailsService(UsuarioFeignClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        UsuarioDTO usuario = null;
        try {
            usuario = this.usuarioClient.findByEmail(email).getBody();
        } catch (Exception e) {
            return null;
        }
        System.out.println("ESTE ES EL USUARIO QUE OBTUVE: " + usuario.toString());
        if(usuario == null)
            return null;

        return this.createSpringSecurityUser(usuario);
    }

    private UserDetails createSpringSecurityUser( UsuarioDTO user ) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toList() );
        return new org.springframework.security.core.userdetails.User( user.getEmail(), user.getPassword(), grantedAuthorities );
    }
}
