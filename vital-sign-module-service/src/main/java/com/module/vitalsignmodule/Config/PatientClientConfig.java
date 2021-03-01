package com.module.vitalsignmodule.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * PatientClientConfig is used to generate patient client for feign.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Configuration
public class PatientClientConfig {
	  
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password");
	}
	
//	@Bean
//	public Contract feignContract() {
//		return new Contract.Default();
//	}
//	
//	@Bean
//	public PatientClient patientServiceClient() {
//		final RequestInterceptor authInterceptor = new BasicAuthRequestInterceptor("user", "password");
//		return Feign.builder()
//				.encoder(new JacksonEncoder())
//				.decoder(new JacksonDecoder())
//				.requestInterceptor(authInterceptor)
//                .target(PatientClient.class, "http://localhost:8082/patients");
//	}
}
