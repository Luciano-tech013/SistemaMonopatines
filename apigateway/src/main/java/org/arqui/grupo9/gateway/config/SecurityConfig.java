package org.arqui.grupo9.gateway.config;

import org.arqui.grupo9.gateway.clients.UsuarioFeignClient;
import org.arqui.grupo9.gateway.security.DomainUserDetailsService;
import org.arqui.grupo9.gateway.security.jwt.JwtFilter;
import org.arqui.grupo9.gateway.security.jwt.TokenProvider;
import org.arqui.grupo9.gateway.security.AuthorityConstant;
import org.arqui.grupo9.gateway.service.dtos.UsuarioDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private DomainUserDetailsService userDetailsService;

    public SecurityConfig(DomainUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public TokenProvider tokenProvider() {
        return new TokenProvider();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable );
        http
                .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests( authz -> authz
                        /*.requestMatchers(HttpMethod.GET, "/api/viajes/monopatines").hasAuthority(String.valueOf(AuthorityConstant.ADMIN))
                        .requestMatchers(HttpMethod.GET, "/api/viajes/facturacion").hasAuthority(String.valueOf(AuthorityConstant.ADMIN))
                        .requestMatchers(HttpMethod.PUT, "/api/viajes/ajustes/precio").hasAuthority(String.valueOf(AuthorityConstant.ADMIN))
                        .requestMatchers(HttpMethod.GET, "/api/viajes/monopatines/monopatin/{idMonopatin}/reporte/tiempopausado").hasAuthority(String.valueOf(AuthorityConstant.ENCARGADO))
                        //.requestMatchers(HttpMethod.GET, "/api/viajes").hasAuthority(String.valueOf(AuthorityConstant.USUARIO))
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/viajes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/monopatines").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/estaciones").permitAll()*/
                        .requestMatchers("/api/viajes").permitAll()
                        .requestMatchers("/api/viajes/generar/usuario/{idUsuario}/monopatin/{idMonopatin}").permitAll()
                        .requestMatchers("/api/viajes/finalizar").permitAll()
                        .requestMatchers("/api/viajes/pausar").permitAll()
                        .requestMatchers("/api/viajes/despausar").permitAll()
                        .requestMatchers("/api/viajes/monopatin").permitAll()
                        .requestMatchers("/api/viajes/facturacion").permitAll()
                        .requestMatchers("/api/viajes/ajuste/precio").permitAll()
                        .requestMatchers("/api/viajes/monopatin/{idMonopatin}/reporte/tiempopausado").permitAll()
                        .requestMatchers("/api/monopatines").permitAll()
                        .requestMatchers("/api/monopatines/{idMonopatin}").permitAll()
                        .requestMatchers("/api/monopatines/reporte/estado").permitAll()
                        .requestMatchers("/api/monopatines/reporte/uso").permitAll()
                        .requestMatchers("/api/monopatines/ubicacion").permitAll()
                        .requestMatchers("/api/roles").permitAll()
                        .requestMatchers("/api/usuarios/{idUsuario}").permitAll()
                        .requestMatchers("/api/usuarios/{idUsuario}/cuentas").permitAll()
                        .requestMatchers("/api/usuarios/{idUsuario}/cuentas/{idCuentaMp}/asociar").permitAll()
                        .requestMatchers("/api/usuarios/{idUsuario}/cuentas/{idCuentaMp}/eliminar").permitAll()
                        .requestMatchers("/api/usuarios/{email}").permitAll()
                        .requestMatchers("/api/mercadopago/cuentas").permitAll()
                        .requestMatchers("/api/mercadopago/cuentas/{idCuentaMp}").permitAll()
                        .requestMatchers("/api/mercadopago/cuentas/{idCuentaMp}/cargarsaldo").permitAll()
                        .requestMatchers("/api/mercadopago/cuentas/{idCuentaMp}/descontarSaldo").permitAll()
                        .requestMatchers("/api/mercadopago/cuentas/{idCuentaMp}/inhabilitar").permitAll()
                        .requestMatchers("/api/estaciones/**").permitAll()
                        .requestMatchers("/api/estaciones/{idEstacion}").permitAll()
                        .requestMatchers("/api/estaciones/ubicacion").permitAll()
                        .anyRequest().denyAll()
                )
                .httpBasic( Customizer.withDefaults() )
                .addFilterBefore( new JwtFilter( this.tokenProvider() ), UsernamePasswordAuthenticationFilter.class );

        return http.build();
    }
}
