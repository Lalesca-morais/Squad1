package br.com.zup.squad1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Sabor&Saúde API")
                        .description("Com o Sabor&Saúde , o usuário terá um controle mais efetivo dos ingredientes, reduzindo o desperdício de alimentos, além disso auxilia a manter a saúde, acompanhando e garantindo a ingestão ideal de água diariamente. Isso não só traz mais eficiência na cozinha, mas também promove um estilo de vida mais saudável e sustentável.\n" +
                                "\nEsperamos que a API Sabor&Saúde torne sua vida mais saudável, eficiente e sustentável.\n" +
                                "\n Para obter detalhes sobre como utilizar nossa API, consulte a documentação completa em [GitHub](https://github.com/Lalesca-morais/Squad1.git)\n" +
                                "\nDeveloper Team:\n\n" +
                                " **Amanda Santos** (amanda.santos@zup.com.br)\n\n" +
                                " **Lalesca Silva** (lalesca.silva@zup.com.br)\n\n" +
                                " **Paula Nascimento** (paula.nascimento@zup.com.br)\n\n" +
                                " **Vitória Ferreira** (vitoria.ferreira@zup.com.br)").version("1.0"));
    }
}