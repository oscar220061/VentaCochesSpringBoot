package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.repository")
@EntityScan(basePackages = "org.example.entity")
@ComponentScan(basePackages = "org.example.controller")
@ComponentScan(basePackages = "org.example.converter")
@ComponentScan(basePackages = "org.example.service")
@ComponentScan(basePackages = "org.example.config")
@EnableFeignClients(basePackages = "org.example.api")


public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        //http://localhost:8080/h2-ui
    }
}