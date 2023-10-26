package br.com.zup.squad1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API Squad 1")
                        .description("API documentation developed by squad 1").version("1.0")
                        .contact(new Contact().name("Developer Team: \n Amanda Santos" +
                                        " \n Lalesca Silva " +
                                        "\n Paula Nascimento " +
                                        "\n Vitória Ferreira")
                                .url("\n https://github.com/Lalesca-morais/Squad1.git \n")
                                .email("amanda.santos@zup.com.br \n" +
                                        "lalesca.silva@zup.com.br \n" +
                                        "paula.nascimento@zup.com.br \n" +
                                        "vitoria.ferreira@zup.com.br")));
    }
}
