package br.com.projetofinal.cordeirostyle.configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Cordeiro Style")
                .description(" - E-commerce -")
                .version("v0.0.1")
                .license(new License()
                    .name("Cordeiro Style")
                    .url("cordeirostyle.com"))
                .contact(new Contact()
                    .name("Grupo 4 Serratec")
                    .url("https://github.com/lucasbonafe1/api-restful-e-commerce")
                    .email("teste@gmail.com")))
            .externalDocs(new ExternalDocumentation()
                .description("Github")
                .url("https://github.com"));
    }
}
