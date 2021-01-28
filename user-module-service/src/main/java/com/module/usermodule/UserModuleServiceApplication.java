package com.module.usermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
@RefreshScope
public class UserModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserModuleServiceApplication.class, args);
	}

}
