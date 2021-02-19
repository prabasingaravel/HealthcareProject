package com.module.vitalsignmodule.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class PatientClientConfig {
	  
	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password");
	}
	
//	@Bean
//	public PatientClient patientServiceClient() {
//		final BasicAuthRequestInterceptor authInterceptor = new BasicAuthRequestInterceptor("user", "password");
//		return Feign.builder()
//				.encoder(new JacksonEncoder())
//				.decoder(new JacksonDecoder())
//				.requestInterceptor(authInterceptor)
//                .target(PatientClient.class, "http://localhost:8082/patients");
//	}
}
