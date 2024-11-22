package org.arqui.grupo9.microservicioviajes.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduledConfig {
    //Esta clase es para que Spring reconozca los metodos con @Scheduled
}
