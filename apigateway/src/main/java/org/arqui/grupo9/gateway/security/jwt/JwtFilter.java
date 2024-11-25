package org.arqui.grupo9.gateway.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.NonNull;
import org.arqui.grupo9.gateway.service.dtos.JwtErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger( JwtFilter.class );

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    public JwtFilter( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken( request );
        try {
            if ( StringUtils.hasText(jwt) && this.tokenProvider.validateToken( jwt ) ) {
                System.out.println("EL TOKEN SE VALIDO CORRECTAMENTEEEEEEEEE");
                Authentication authentication = this.tokenProvider.getAuthentication( jwt );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch ( ExpiredJwtException e ) {
            log.info( "REST request UNAUTHORIZED - La sesión ha expirado." );
            response.setStatus( 498 );
            response.setContentType( MediaType.APPLICATION_JSON_VALUE );
            response.getWriter().write( new JwtErrorDTO().toJson() );
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken( HttpServletRequest request ) {
        String bearerToken = request.getHeader( AUTHORIZATION_HEADER );
        if ( StringUtils.hasText( bearerToken ) && bearerToken.startsWith( "Bearer " ) ) {
            return bearerToken.substring(7 );
        }
        return null;
    }
}
