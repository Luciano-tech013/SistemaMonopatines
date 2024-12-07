package org.arqui.grupo9.microserviciocuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicroserviciocuentasApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviciocuentasApplication.class, args);
    }

}
