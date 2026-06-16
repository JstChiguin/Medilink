package medilink.evaluacionatencion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("API 2026 Evaluación de Atención")
                                .version("1.0")
                                .description(
                                        "Microservicio de Evaluación de Atención de MediLink"
                                )
                                .contact(
                                        new Contact()
                                                .name("Chiguin")
                                                .email("chiguin@medilink.cl")
                                )
                );
    }
}