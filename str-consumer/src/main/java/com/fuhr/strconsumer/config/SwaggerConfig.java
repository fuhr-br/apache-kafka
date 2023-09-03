package com.fuhr.strconsumer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI apiInfo() {

    return new OpenAPI()
        .info(
            new Info()
                .title("API de Demonstração de serviço de mensageria")
                .description("Consome mensagens de um tópico no kafka")
                .contact(new Contact()
                    .name("Anderson Fuhr")
                    .email("andersonfuhr.afs@gmail.com")
                    .url("https://github.com/fuhr-br")
                )
                .version("1.0.0")
                .license(new License())
                .termsOfService("")
        );
  }
}