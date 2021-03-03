package com.module.healthcareaudit;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ListenerExecutionFailedException;

import com.module.healthcareaudit.Config.KafkaConfig;
import com.module.healthcareaudit.Dto.AuditDto;
import com.module.healthcareaudit.Model.Audit;
import com.module.healthcareaudit.Repository.AuditRepository;
import com.module.healthcareaudit.Service.AuditService;
import com.module.healthcareaudit.Util.AuditConverter;

/**
 * HealthcareAuditTrailApplication contains main method of health care audit trail and kafka listener.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@SpringBootApplication
public class HealthcareAuditTrailApplication {

	Logger logger = LoggerFactory.getLogger(HealthcareAuditTrailApplication.class);
	
	private final AuditRepository auditRepository;
	
	private final AuditService auditService;

	@Autowired
	public HealthcareAuditTrailApplication(AuditRepository auditRepository, AuditService auditService) {
		this.auditRepository = auditRepository;
		this.auditService = auditService;
	}

	/**
	 * consume method is used to consume kafka messages.
	 * @param auditDto
	 */
	@KafkaListener(topics = "Audit_Topic", groupId = "healthcare_group", containerFactory = "healthKafkaListenerContainerFactory")
	public void consume(AuditDto auditDto) throws ParseException {
		logger.info("Received Messasge: {}", auditDto);
	    try {
	    	auditDto.setRequest(auditService.findRequestType(auditDto.getRequest()));
			auditDto.setAction(auditService.findAction(auditDto.getRequest(),auditDto.getServiceName()));
			Audit audit= AuditConverter.convertToAuditEntity(auditDto);
			auditRepository.save(audit);
	    } catch (Exception e) {
	        throw new ListenerExecutionFailedException(e.getLocalizedMessage(), e);
	    }
	}
	
	/**
	 * main method is the entry point of health care audit trail service.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(HealthcareAuditTrailApplication.class, args);
	}

}
