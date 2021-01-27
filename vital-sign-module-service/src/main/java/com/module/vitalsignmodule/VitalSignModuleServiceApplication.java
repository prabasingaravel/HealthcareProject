package com.module.vitalsignmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.module.vitalsignmodule")
public class VitalSignModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VitalSignModuleServiceApplication.class, args);
	}

}
