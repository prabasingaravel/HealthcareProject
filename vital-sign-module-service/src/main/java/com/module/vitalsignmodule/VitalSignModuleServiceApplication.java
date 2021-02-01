package com.module.vitalsignmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.module.vitalsignmodule")
@EnableDiscoveryClient
public class VitalSignModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalSignModuleServiceApplication.class, args);
	}

}
