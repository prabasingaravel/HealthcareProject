package com.module.healthcareaudit.Config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.module.healthcareaudit.Dto.AuditDto;

/**
 * KafkaPublisherConfig is used to generate the producer kafka template.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Configuration
@EnableKafka
public class KafkaConfig {

	@Bean
	public ConsumerFactory<String, AuditDto> healthConsumerFactory(){
		JsonDeserializer<AuditDto> deserializer = new JsonDeserializer<>(AuditDto.class);
	    deserializer.setRemoveTypeHeaders(false);
	    deserializer.addTrustedPackages("*");
	    deserializer.setUseTypeMapperForKey(true);
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "healthcare_group");
		return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),deserializer);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AuditDto> healthKafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, AuditDto> factory = new ConcurrentKafkaListenerContainerFactory<String, AuditDto>();
		factory.setConsumerFactory(healthConsumerFactory());
		return factory;
	}
}
