package com.module.usermodule.Advice;

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
import com.module.usermodule.Dto.AuditDto;
import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Repository.UserRepository;

@Aspect
@Component
public class AuditTrailLoggingAdvice {
	
	private final UserRepository userRepository;
	
	KafkaTemplate<String, AuditDto> kafkaTemplate;

	private static final String KAFKA_TOPIC = "Audit_Topic";
	
	@Autowired
	public AuditTrailLoggingAdvice(KafkaTemplate<String, AuditDto> kafkaTemplate, 
			UserRepository userRepository) {
		this.kafkaTemplate = kafkaTemplate;
		this.userRepository = userRepository;
	}
	
	@Around("@annotation(com.module.usermodule.Advice.AuditTrailLogging)")
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
		UserDto userDto = (UserDto) array[0];
		long userid = userDto.getUserId();
		Object oldValue = userRepository.findByUserId(userid);
		if(Objects.nonNull(oldValue)) {
			auditDto.setOldValue(oldValue);
		} else {
			auditDto.setOldValue(StringUtils.EMPTY);
		}
		Object object = pjp.proceed();
		auditDto.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		auditDto.setServiceName("User");
		auditDto.setRequest(methodName);
		auditDto.setAction(StringUtils.EMPTY);
		auditDto.setNewValue(object);
		auditDto.setLogDate(date.toString());
		auditDto.setLogTime(time.toString());
		kafkaTemplate.send(KAFKA_TOPIC, auditDto);
		return object;
	}
}