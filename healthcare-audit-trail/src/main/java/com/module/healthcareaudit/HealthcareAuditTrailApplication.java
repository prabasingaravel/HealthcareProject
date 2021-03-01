package com.module.healthcareaudit;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import com.module.healthcareaudit.Dto.AuditDto;
import com.module.healthcareaudit.Model.Audit;
import com.module.healthcareaudit.Repository.AuditRepository;
import com.module.healthcareaudit.Service.AuditService;
import com.module.healthcareaudit.Util.AuditConverter;

@SpringBootApplication
public class HealthcareAuditTrailApplication {

	private final AuditRepository auditRepository;
	
	private final AuditService auditService;

	@Autowired
	public HealthcareAuditTrailApplication(AuditRepository auditRepository, AuditService auditService) {
		this.auditRepository = auditRepository;
		this.auditService = auditService;
	}

	@KafkaListener(topics = "Audit_Topic", groupId = "healthcare_group", containerFactory = "healthKafkaListenerContainerFactory")
	public void consume(AuditDto auditDto) throws ParseException {
		auditDto.setRequest(auditService.findRequestType(auditDto.getRequest()));
		auditDto.setAction(auditService.findAction(auditDto.getRequest(),auditDto.getServiceName()));
		Audit audit= AuditConverter.convertToAuditEntity(auditDto);
		auditRepository.save(audit);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(HealthcareAuditTrailApplication.class, args);
	}

}
