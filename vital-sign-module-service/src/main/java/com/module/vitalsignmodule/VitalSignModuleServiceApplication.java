package com.module.vitalsignmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@RefreshScope
@EnableFeignClients("com.module.vitalsignmodule")
@EnableDiscoveryClient
@EnableJpaAuditing
public class VitalSignModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalSignModuleServiceApplication.class, args);
	}

}
