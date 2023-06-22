package com.MovieApps.config;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Movie Booking API",
        version = "1.0",
        description = "API for movie booking application"
    )
)
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi customOpenAPI() {
        String[] paths = {"/register/**", "/login/**", "/reset-password/**"};
        return GroupedOpenApi.builder().group("movie-booking").pathsToMatch(paths).build();
    }
}

