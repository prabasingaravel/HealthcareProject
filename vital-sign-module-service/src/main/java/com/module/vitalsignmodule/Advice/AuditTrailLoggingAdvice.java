package com.module.vitalsignmodule.Advice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.module.vitalsignmodule.Dto.AuditDto;
import com.module.vitalsignmodule.Dto.DataDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Repository.VitalSignRepository;
import com.module.vitalsignmodule.Util.VitalSignConverter;

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
		AuditDto auditDto = new AuditDto();
		String methodName = joinPoint.getSignature().getName();
		Object[] array = joinPoint.getArgs();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
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
		VitalSignDto oldValue = VitalSignConverter.convertToVitalSignDto(vitalsignRepository.findByPatientIdAndCheckupDate(patientid,checkupDate));
		Object object = joinPoint.proceed();
		auditDto.setUserName("Sanju");
		auditDto.setServiceName("VitalSign");
		auditDto.setRequest(methodName);
		auditDto.setAction(StringUtils.EMPTY);
		VitalSignDto newValue = (VitalSignDto) object;
		DataDto dataDto = new DataDto();
		List<String> fieldName = new ArrayList<>();
		List<Object> oldList =  new ArrayList<>();
		List<Object> newList =  new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		if(Objects.nonNull(oldValue)) {
			if(!(oldValue.getUserName().equals(newValue.getUserName()))) {
				fieldName.add("User Name");
				oldList.add(oldValue.getUserName());
				newList.add(newValue.getUserName());
			}
			calendar.setTime(newValue.getCheckupDate());
			String formatedNewCheackupDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);

			calendar.setTime(oldValue.getCheckupDate());
			String formatedOldCheackupDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
			if(!(formatedOldCheackupDate.equals(formatedNewCheackupDate))) {
				fieldName.add("Checkup Date");
				oldList.add(formatedOldCheackupDate);
				newList.add(formatedNewCheackupDate);
			}
			if(!(oldValue.getPulse() == newValue.getPulse())) {
				fieldName.add("Pulse Rate");
				oldList.add(oldValue.getPulse());
				newList.add(newValue.getPulse());
			}
			if(!(oldValue.getBloodPressure() == newValue.getBloodPressure())) {
				fieldName.add("Blood Pressure");
				oldList.add(oldValue.getBloodPressure());
				newList.add(newValue.getBloodPressure());
			}
			if(!(oldValue.getWeight() == newValue.getWeight())) {
				fieldName.add("Weight");
				oldList.add(oldValue.getWeight());
				newList.add(newValue.getWeight());
			}
			if(!(oldValue.getTemperature() == newValue.getTemperature())) {
				fieldName.add("Body Temperature");
				oldList.add(oldValue.getTemperature());
				newList.add(newValue.getTemperature());
			}
			if(!(oldValue.getBloodSugar() == newValue.getBloodSugar())) {
				fieldName.add("Blood Sugar");
				oldList.add(oldValue.getBloodSugar());
				newList.add(newValue.getBloodSugar());
			}
			if(!(oldValue.getRespirationRate() == newValue.getRespirationRate())) {
				fieldName.add("Respiration Rate");
				oldList.add(oldValue.getRespirationRate());
				newList.add(newValue.getRespirationRate());
			}
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		}else {
			fieldName.add("User Name");
			newList.add(newValue.getUserName());
			calendar.setTime(newValue.getCheckupDate());
			String formatedNewCheackupDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
			fieldName.add("Checkup Date");
			newList.add(formatedNewCheackupDate);
			fieldName.add("Pulse Rate");
			newList.add(newValue.getPulse());
			fieldName.add("Blood Pressure");
			newList.add(newValue.getBloodPressure());
			fieldName.add("Weight");
			newList.add(newValue.getWeight());
			fieldName.add("Body Temperature");
			newList.add(newValue.getTemperature());
			fieldName.add("Blood Sugar");
			newList.add(newValue.getBloodSugar());
			fieldName.add("Respiration Rate");
			newList.add(newValue.getRespirationRate());
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		}
		auditDto.setLogDate(date.toString());
		auditDto.setLogTime(time.toString());
		kafkaTemplate.send(KAFKA_TOPIC, auditDto);
		return object;
	}
}