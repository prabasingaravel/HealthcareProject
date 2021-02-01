package com.module.patientmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
public class PatientModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientModuleServiceApplication.class, args);
	}

}
