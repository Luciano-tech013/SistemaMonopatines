package org.arqui.grupo9.microservicioestaciones.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.arqui.grupo9.microservicioestaciones.repository")
public class MongoConfig {

}

