package com.module.patientmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;

import io.opentracing.Tracer;

/**
 * PatientModuleServiceApplication contains main method of patient service.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
@EnableJpaAuditing
public class PatientModuleServiceApplication {

	/**
	 * jaegerTracer method is the sampler configuration for jaeger tracer.
	 * @return Tracer
	 */
	@Bean
	public Tracer jaegerTracer() {
		return new Configuration("patient module",new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE,1),
				new Configuration.ReporterConfiguration()).getTracer();
	}
	
	/**
	 * main method is the entry point of patient service.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PatientModuleServiceApplication.class, args);
	}

}
