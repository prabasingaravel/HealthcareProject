package com.module.vitalsignmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * VitalSignModuleServiceApplication contains main method of vital sign service.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@SpringBootApplication
@EnableCaching
@EnableFeignClients("com.module.vitalsignmodule")
@EnableDiscoveryClient
@EnableJpaAuditing
public class VitalSignModuleServiceApplication {

	/**
	 * main method is the entry point of vital sign service.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(VitalSignModuleServiceApplication.class, args);
	}

}
