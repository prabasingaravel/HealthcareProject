package com.module.patientmodule.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig is used to generate the end point detail documentation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */	
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * docket is used to configure the end point document to the swagger.
	 * @return Docket
	 *
	 */
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"))).build();
	}
	
	/**
	 * apiInfo is used to build the detail information of the end point.
	 * @return ApiInfo
	 *
	 */
	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Patient Details")
				.description("API can be used to get Details and vital signs for patient")
				.version("V2.0").build();
	}
	
}
