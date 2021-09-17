package com.emainfo.cra.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(getComponents())
				.info(new Info())
				.addSecurityItem(new SecurityRequirement()
			    .addList("Bearer"));
	}

	private Components getComponents() {
		final SecurityScheme authorizationHeaderSchema = new SecurityScheme()
				.name("Authorization")
				.type(SecurityScheme.Type.APIKEY)
				.in(SecurityScheme.In.HEADER);

		return new Components().securitySchemes(Map.of("Bearer", authorizationHeaderSchema));
	}
}
