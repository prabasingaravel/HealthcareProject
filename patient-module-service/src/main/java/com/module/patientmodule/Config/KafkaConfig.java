package com.module.patientmodule.Config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.module.patientmodule.Dto.AuditDto;

/**
 * KafkaPublisherConfig is used to generate the producer kafka template.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Configuration
public class KafkaConfig {

	@Bean
	public ProducerFactory<String, AuditDto> producerFactory(){
		Map<String, Object> configs = new HashMap<>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, AuditDto>(configs);
	}
	
	@Bean
	public KafkaTemplate<String, AuditDto> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}
