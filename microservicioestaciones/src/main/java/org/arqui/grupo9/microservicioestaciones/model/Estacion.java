package org.arqui.grupo9.microservicioestaciones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "estaciones")
@Data
@AllArgsConstructor
@Getter
public class Estacion {
    @Id
    private String _id;

    @Field("nombre")
    private String nombre;

    @Field("latitud")
    private Double latitud;

    @Field("longitud")
    private Double longitud;

    public Estacion() {}
}
