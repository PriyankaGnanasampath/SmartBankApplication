package com.smartbank.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI smartBankOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("SmartBank API")
				.description("Enterprise Banking Backend Application")
				.version("1.0")
				.contact(new Contact()
						.name("Priya G")
						.email("priya@gmail.com"))
		);
	}}
