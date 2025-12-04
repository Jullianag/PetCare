package com.petcare.petcare.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {

    @Bean
    public OpenAPI petcareAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Petcare API")
                        .description("PetCare Reference Project")
                        .version("v0.0.1")
                        .license(new License()
                                .name("MIT License")
                                .url("https://github.com/Jullianag/PetCare/blob/main/LICENSE")));
    }
}
