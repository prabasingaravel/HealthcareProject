package com.module.healthcareaudit.Config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
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
	
	Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

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
		ErrorHandlingDeserializer<AuditDto> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(new JsonDeserializer<>(AuditDto.class));
		return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),errorHandlingDeserializer);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AuditDto> healthKafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, AuditDto> factory = new ConcurrentKafkaListenerContainerFactory<String, AuditDto>();
		factory.setConsumerFactory(healthConsumerFactory());
		factory.setErrorHandler(new SeekToCurrentErrorHandler());
//		factory.setErrorHandler(((exception, data) -> {
//			logger.error("Error in process with Exception {} and the record is {}", exception, data);
//        }));
		return factory;
	}
}
