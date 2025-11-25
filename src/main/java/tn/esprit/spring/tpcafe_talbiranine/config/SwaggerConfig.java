package tn.esprit.spring.tpcafe_talbiranine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("☕ TPCafe Talbiranine API")
                        .version("1.0.0")
                        .description("""
                                API documentation for the TPCafe Talbiranine project.  
                                Manage clients, commandes, promotions, detail commandes, and more efficiently.
                                """)
                        .contact(new Contact()
                                .name("Talbiranine Team")
                                .email("talbiranine3@gmail.com")
                                .url("https://www.linkedin.com/in/ranine-talbi-ba9aa1354")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository")
                        .url("https://github.com/ranine1talbi"));
    }

    // Grouped APIs with corrected paths matching the controllers

    @Bean
    public GroupedOpenApi adresseApi() {
        return GroupedOpenApi.builder()
                .group("Adresse")
                .pathsToMatch("/adresse/**")  // matches AdresseRestController
                .build();
    }

    @Bean
    public GroupedOpenApi articleApi() {
        return GroupedOpenApi.builder()
                .group("Article")
                .pathsToMatch("/article/**")
                .build();
    }

    @Bean
    public GroupedOpenApi carteFideliteApi() {
        return GroupedOpenApi.builder()
                .group("CarteFidelite")
                .pathsToMatch("/carte-fidelite/**")
                .build();
    }

    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder()
                .group("Client")
                .pathsToMatch("/client/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commandeApi() {
        return GroupedOpenApi.builder()
                .group("Commande")
                .pathsToMatch("/commande/**")
                .build();
    }

    @Bean
    public GroupedOpenApi detailCommandeApi() {
        return GroupedOpenApi.builder()
                .group("Detail_Commande")
                .pathsToMatch("/details-commandes/**")  // matches Detail_CommandeRestController
                .build();
    }

    @Bean
    public GroupedOpenApi promotionApi() {
        return GroupedOpenApi.builder()
                .group("Promotion")
                .pathsToMatch("/promotions/**")  // matches PromotionRestController
                .build();
    }
}
