package com.module.vitalsignmodule.Advice;

import java.util.Date;
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
import com.module.vitalsignmodule.Dto.AuditDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Repository.VitalSignRepository;

/**
 * AuditTrailLoggingAdvice is used for Auditing the CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Aspect
@Component
public class AuditTrailLoggingAdvice {
	
	private final VitalSignRepository vitalsignRepository;
	
	KafkaTemplate<String, AuditDto> kafkaTemplate;

	private static final String KAFKA_TOPIC = "Audit_Topic";
	
	@Autowired
	public AuditTrailLoggingAdvice(KafkaTemplate<String, AuditDto> kafkaTemplate, 
			VitalSignRepository vitalsignRepository) {
		this.kafkaTemplate = kafkaTemplate;
		this.vitalsignRepository = vitalsignRepository;
	}
	
	/**
	 * auditLogger is used to auditing the controller method.
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 *
	 */
	@Around("@annotation(com.module.vitalsignmodule.Advice.AuditTrailLogging)")
	public Object auditLogger(ProceedingJoinPoint joinPoint) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		AuditDto auditDto = new AuditDto();
		String methodName = joinPoint.getSignature().getName();
		Object[] array = joinPoint.getArgs();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
		String charToDel = "{[]}";
		String pattern = "[" + Pattern.quote(charToDel) + "]";
		VitalSignDto vitalsignDto;
		int patientid;
		Date checkupDate;
		if(array.length != 1) {
			patientid = (int) array[0];
			checkupDate = (Date) array[1];
		}else {
			vitalsignDto = (VitalSignDto) array[0];
			patientid = vitalsignDto.getPatientId();
			checkupDate = vitalsignDto.getCheckupDate();
		}
		
		Object oldValue = vitalsignRepository.findByPatientIdAndCheckupDate(patientid,checkupDate);
		if(Objects.nonNull(oldValue)) {
			auditDto.setOldValue(oldValue);
		} else {
			auditDto.setOldValue(StringUtils.EMPTY);
		}
		Object object = joinPoint.proceed();
		auditDto.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		auditDto.setServiceName("VitalSign");
		auditDto.setRequest(methodName);
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setNewValue(object);
		auditDto.setLogDate(date.toString());
		auditDto.setLogTime(time.toString());
		kafkaTemplate.send(KAFKA_TOPIC, auditDto);
		return object;
	}
}