package com.module.usermodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * UserModuleServiceApplication contains main method of user service.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableZuulProxy
public class UserModuleServiceApplication {

	/**
	 * main method is the entry point of user service.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserModuleServiceApplication.class, args);
	}

}
