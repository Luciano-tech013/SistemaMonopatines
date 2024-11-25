package org.arqui.grupo9.gateway.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.arqui.grupo9.gateway.security.jwt.JwtFilter;
import org.arqui.grupo9.gateway.security.jwt.TokenProvider;
import org.arqui.grupo9.gateway.service.dtos.LoginDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate" )
public class JwtController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public JwtController(TokenProvider tokenProvider, @Lazy AuthenticationManager authenticationManager) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDTO request ) {
        System.out.println("ACA LLEGUEEEEE");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authentication = authenticationManager.authenticate( authenticationToken );
        System.out.println("POR ACA PASEEEEEEEEEEEEEE");
        SecurityContextHolder.getContext().setAuthentication( authentication );
        final var jwt = tokenProvider.createToken( authentication );
        System.out.println("SE CREO EL TOKEN: " + jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );

        return new ResponseEntity<>( new JWTToken( jwt ), httpHeaders, HttpStatus.OK );
    }

    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
