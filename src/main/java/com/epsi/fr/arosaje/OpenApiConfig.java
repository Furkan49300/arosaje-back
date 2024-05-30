package com.epsi.fr.arosaje;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Titre de l'API")
                        .version("1.0")
                        .description("Description de l'API")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Nom du contact")
                                .email("email@domain.com")
                                .url("http://www.domain.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
