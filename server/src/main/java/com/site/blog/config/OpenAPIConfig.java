package com.site.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Post API",
                version = "1.0.0",
                description = "A simple API for managing blog posts."
        )
)
public class OpenAPIConfig {
}
