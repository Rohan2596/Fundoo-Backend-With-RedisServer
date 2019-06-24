package com.bridgelabz.fundoo.configuartion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Rohan Kadam Purpose:Swagger Configuration Bean Providers
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Purpose: For processing the bean for swagger configuration
	 * 
	 * @return
	 */
	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2)

				.select().apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundoo"))

				.build();

	}
}
