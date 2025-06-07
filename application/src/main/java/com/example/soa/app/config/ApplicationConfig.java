package com.example.soa.app.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuração principal da aplicação Spring Boot.
 * Esta classe configura o escaneamento de componentes, entidades e repositórios.
 */
@Configuration
@ComponentScan(basePackages = {
    "com.example.soa.app",
    "com.example.soa.rest",
    "com.example.soa.soap",
    "com.example.soa.service.impl",
    "com.example.soa.data.mapper"
})
@EntityScan(basePackages = "com.example.soa.data.entity")
@EnableJpaRepositories(basePackages = "com.example.soa.data.repository")
public class ApplicationConfig {
    // Configurações adicionais podem ser adicionadas aqui
}

