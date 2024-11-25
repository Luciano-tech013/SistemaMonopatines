package org.arqui.grupo9.gateway.service.dtos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

public class JwtErrorDTO {
    private final String message = "Token expired";
    private final String date = LocalDateTime.now().toString();

    public JwtErrorDTO(){}

    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (RuntimeException | JsonProcessingException ex ) {
            return String.format("{ message: %s }", this.message );
        }
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate() {
        return this.date;
    }
}
