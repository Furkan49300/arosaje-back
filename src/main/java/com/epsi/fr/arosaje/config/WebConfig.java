package com.epsi.fr.arosaje.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/healthz")
                        .allowedOrigins("*") // Autoriser les requêtes depuis n'importe quelle origine
                        .allowedMethods("GET"); // Autoriser uniquement les requêtes GET
                registry.addMapping("/api")
                        .allowedOrigins("*") // Autoriser les requêtes depuis n'importe quelle origine
                        .allowedMethods("POST"); // Autoriser uniquement les requêtes POST
            }
        };
    }
}
