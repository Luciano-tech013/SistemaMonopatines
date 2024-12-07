package org.arqui.grupo9.serviciomercadopago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiciomercadopagoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiciomercadopagoApplication.class, args);
    }

}
