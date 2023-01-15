package br.com.gabrielmotta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private static final String DESCRICAO = "Documentação da api de Pedidos";
    private static final String URL = "";
    private static final String ADMIN_GROUP = "admin";
    private static final String PATHS_ADMIN_GROUP = "/**";

    @Value("${app-config.versao}")
    private String versao;
    @Value("${app-config.nome}")
    private String nome;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/docs", "/swagger-ui.html");
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
            .group(ADMIN_GROUP)
            .pathsToMatch(PATHS_ADMIN_GROUP)
            .build();
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
            .info(new Info().title(nome)
                .description(DESCRICAO)
                .version(versao)
                .license(new License().url(URL)));
    }
}
