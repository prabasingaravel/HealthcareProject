package com.module.usermodule.Advice;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.module.usermodule.Dto.AuditDto;
import com.module.usermodule.Dto.DataDto;
import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Repository.UserRepository;
import com.module.usermodule.Util.UserConverter;

/**
 * AuditTrailLoggingAdvice is used for Auditing the CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
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
	
	/**
	 * auditLogger is used to auditing the controller method.
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 *
	 */
	@Around("@annotation(com.module.usermodule.Advice.AuditTrailLogging)")
	public Object auditLogger(ProceedingJoinPoint pjp) throws Throwable {
		AuditDto auditDto = new AuditDto();
		String methodName = pjp.getSignature().getName();
		Object[] array = pjp.getArgs();
		long currentTimeMillis = System.currentTimeMillis();
		Date currentLoggingDate = new Date(currentTimeMillis);
		Time currentLoggingTime = new Time(currentTimeMillis);
		UserDto userDto = (UserDto) array[0];
		long userid = userDto.getUserId();
		UserDto oldValue = UserConverter.convertToUserDto(userRepository.findByUserId(userid));
		Object object = pjp.proceed();
		auditDto.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		auditDto.setServiceName("User");
		auditDto.setRequest(methodName);
		auditDto.setAction(StringUtils.EMPTY);
		UserDto newValue = (UserDto) object;
		DataDto dataDto = new DataDto();
		List<String> fieldName = new ArrayList<>();
		List<Object> oldList =  new ArrayList<>();
		List<Object> newList =  new ArrayList<>();
		if(Objects.nonNull(oldValue)) {
			if(!(oldValue.getUserName().equals(newValue.getUserName()))) {
				fieldName.add("User Name");
				oldList.add(oldValue.getUserName());
				newList.add(newValue.getUserName());
			}
			if(!(oldValue.getPassword().equals(newValue.getPassword()))) {
				fieldName.add("Password");
				oldList.add(oldValue.getPassword());
				newList.add(newValue.getPassword());
			}
			if(!(oldValue.getRoleId() == newValue.getRoleId())) {
				fieldName.add("Role Id");
				oldList.add(oldValue.getRoleId());
				newList.add(newValue.getRoleId());
			}
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		}else {
			fieldName.add("User Name");
			newList.add(newValue.getUserName());
			fieldName.add("Password");
			newList.add(newValue.getPassword());
			fieldName.add("Role Id");
			newList.add(newValue.getRoleId());
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		}
		auditDto.setLogDate(currentLoggingDate.toString());
		auditDto.setLogTime(currentLoggingTime.toString());
		kafkaTemplate.send(KAFKA_TOPIC, auditDto);
		return object;
	}
}