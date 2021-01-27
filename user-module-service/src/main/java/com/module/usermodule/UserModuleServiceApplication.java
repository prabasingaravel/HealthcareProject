package com.module.usermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UserModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserModuleServiceApplication.class, args);
	}

}
