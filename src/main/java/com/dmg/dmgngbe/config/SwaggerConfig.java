package com.dmg.dmgngbe.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.ServletContext;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI(ServletContext servletContext) {

    Info info = new Info().title("Damoang NG API");

    Server server = new Server().url(servletContext.getContextPath())
        .description("Server");

    var jwtName = "JWT";

    SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtName);
    Components components = new Components()
        .addSecuritySchemes(jwtName, new SecurityScheme()
            .name(jwtName).type(SecurityScheme.Type.HTTP)
            .scheme("bearer").bearerFormat(jwtName));

    return new OpenAPI()
        .servers(List.of(server))
        .addSecurityItem(securityRequirement)
        .components(components)
        .info(info)
        ;
  }

}

