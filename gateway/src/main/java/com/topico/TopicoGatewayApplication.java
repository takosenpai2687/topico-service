package com.topico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TopicoGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicoGatewayApplication.class, args);
    }
}
