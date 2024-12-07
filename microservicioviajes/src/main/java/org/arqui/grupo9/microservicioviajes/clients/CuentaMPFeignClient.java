package org.arqui.grupo9.microservicioviajes.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "microserviciocuentas")
public interface CuentaMPFeignClient {

}
