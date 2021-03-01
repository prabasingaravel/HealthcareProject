package com.module.patientmodule.Advice;

import java.util.Objects;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.patientmodule.Dto.AuditDto;
import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.Repository.PatientRepository;

@Aspect
@Component
public class AuditTrailLoggingAdvice {
	
	private final PatientRepository patientRepository;
	
	KafkaTemplate<String, AuditDto> kafkaTemplate;

	private static final String KAFKA_TOPIC = "Audit_Topic";
	
	@Autowired
	public AuditTrailLoggingAdvice(KafkaTemplate<String, AuditDto> kafkaTemplate, 
			PatientRepository patientRepository) {
		this.kafkaTemplate = kafkaTemplate;
		this.patientRepository = patientRepository;
	}
	
	@Around("@annotation(com.module.patientmodule.Advice.AuditTrailLogging)")
	public Object auditLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		AuditDto auditDto = new AuditDto();
		String methodName = pjp.getSignature().getName();
		Object[] array = pjp.getArgs();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
		String charToDel = "{[]}";
		String pattern = "[" + Pattern.quote(charToDel) + "]";
		PatientDto patientDto = (PatientDto) array[0];
		int patientid = patientDto.getPatientId();
		Object oldValue = patientRepository.getPatientById(patientid);
		if(Objects.nonNull(oldValue)) {
			auditDto.setOldValue(oldValue);
		} else {
			auditDto.setOldValue(StringUtils.EMPTY);
		}
		Object object = pjp.proceed();
		auditDto.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		auditDto.setServiceName("Patient");
		auditDto.setRequest(methodName);
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setNewValue(object);
		auditDto.setLogDate(date.toString());
		auditDto.setLogTime(time.toString());
		kafkaTemplate.send(KAFKA_TOPIC, auditDto);
		return object;
	}
}