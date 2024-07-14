package fiap.salaobeleza.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("salao-beleza")
                .packagesToScan("fiap.salaobeleza.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Salão de Beleza API")
                        .version("v1")
                        .description("Documentação da API do Salão de Beleza")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .url("URL do seu site/projeto")
                                .email("seu-email@dominio.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}