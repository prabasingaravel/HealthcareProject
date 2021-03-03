package com.module.patientmodule.Advice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.module.patientmodule.Dto.AuditDto;
import com.module.patientmodule.Dto.DataDto;
import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.Repository.PatientRepository;
import com.module.patientmodule.Util.PatientConverter;

/**
 * AuditTrailLoggingAdvice is used for Auditing the CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
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

	/**
	 * auditLogger is used to auditing the controller method.
	 * @param joinPoint
	 * @return Object
	 * @throws Throwable
	 *
	 */
	@Around("@annotation(com.module.patientmodule.Advice.AuditTrailLogging)")
	public Object auditLogger(ProceedingJoinPoint pjp) throws Throwable {
		AuditDto auditDto = new AuditDto();
		String methodName = pjp.getSignature().getName();
		Object[] array = pjp.getArgs();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
		PatientDto patientDto = (PatientDto) array[0];
		int patientid = patientDto.getPatientId();
		PatientDto oldValue = PatientConverter.convertToPatientDto(patientRepository.getPatientById(patientid));
		Object object = pjp.proceed();
		auditDto.setUserName("Praba");
		auditDto.setServiceName("Patient");
		auditDto.setRequest(methodName);
		auditDto.setAction(StringUtils.EMPTY);
		PatientDto newValue = (PatientDto) object;
		DataDto dataDto = new DataDto();
		List<String> fieldName = new ArrayList<>();
		List<Object> oldList =  new ArrayList<>();
		List<Object> newList =  new ArrayList<>(); 
		Calendar calendar = Calendar.getInstance();
		if(Objects.nonNull(oldValue)) {
			if(!(oldValue.getPatientFirstName().equals(newValue.getPatientFirstName()))) {
				fieldName.add("Patient First Name");
				oldList.add(oldValue.getPatientFirstName());
				newList.add(newValue.getPatientFirstName());
			}
			if(!(oldValue.getPatientLastName().equals(newValue.getPatientLastName()))) {
				fieldName.add("Patient Last Name");
				oldList.add(oldValue.getPatientLastName());
				newList.add(newValue.getPatientLastName());
			}
			calendar.setTime(newValue.getDob());
			String formatedNewDob = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);

			calendar.setTime(oldValue.getDob());
			String formatedOldDob = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
			if(!(formatedNewDob.equals(formatedOldDob))) {
				fieldName.add("DOB");
				oldList.add(formatedOldDob);
				newList.add(formatedNewDob);
			}
			if(!(oldValue.getAge() == newValue.getAge())) {
				fieldName.add("Age");
				oldList.add(oldValue.getAge());
				newList.add(newValue.getAge());
			}
			if(!(oldValue.getGender().equals(newValue.getGender()))) {
				fieldName.add("Gender");
				oldList.add(oldValue.getGender());
				newList.add(newValue.getGender());
			}
			if(!(oldValue.getMaritalStatus().equals(newValue.getMaritalStatus()))) {
				fieldName.add("Marital Status");
				oldList.add(oldValue.getMaritalStatus());
				newList.add(newValue.getMaritalStatus());
			}
			if(!(oldValue.getContactNo() == newValue.getContactNo())) {
				fieldName.add("Contact No");
				oldList.add(oldValue.getContactNo());
				newList.add(newValue.getContactNo());
			}
			if(!(oldValue.getEmailId().equals(newValue.getEmailId()))) {
				fieldName.add("Email Id");
				oldList.add(oldValue.getEmailId());
				newList.add(newValue.getEmailId());
			}
			if(!(oldValue.getAddress().equals(newValue.getAddress()))) {
				fieldName.add("Address");
				oldList.add(oldValue.getAddress());
				newList.add(newValue.getAddress());
			}
			if(!(oldValue.getPostalCode() == newValue.getPostalCode())) {
				fieldName.add("Postal Code");
				oldList.add(oldValue.getPostalCode());
				newList.add(newValue.getPostalCode());
			}
			if(!(oldValue.getCity().equals(newValue.getCity()))) {
				fieldName.add("City");
				oldList.add(oldValue.getCity());
				newList.add(newValue.getCity());
			}
			if(!(oldValue.getCountry().equals(newValue.getCountry()))) {
				fieldName.add("Country");
				oldList.add(oldValue.getCountry());
				newList.add(newValue.getCountry());
			}
			calendar.setTime(newValue.getRegDate());
			String formatedNewRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);

			calendar.setTime(oldValue.getRegDate());
			String formatedOldRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
			if(!(formatedNewRegDate.equals(formatedOldRegDate))) {
				fieldName.add("Register Date");
				oldList.add(formatedOldRegDate);
				newList.add(formatedNewRegDate);
			}
			dataDto.setFieldName(fieldName);
			dataDto.setOldValue(oldList);
			dataDto.setNewValue(newList);
			auditDto.setData(dataDto);
		}else {
			fieldName.add("Patient First Name");
			newList.add(newValue.getPatientFirstName());
			fieldName.add("Patient Last Name");
			newList.add(newValue.getPatientLastName());
			calendar.setTime(newValue.getDob());
			String formatedNewDob = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
			fieldName.add("DOB");
			newList.add(formatedNewDob);
			fieldName.add("Age");
			newList.add(newValue.getAge());
			fieldName.add("Gender");
			newList.add(newValue.getGender());
			fieldName.add("Marital Status");
			newList.add(newValue.getMaritalStatus());
			fieldName.add("Contact No");
			newList.add(newValue.getContactNo());
			fieldName.add("Email Id");
			newList.add(newValue.getEmailId());
			fieldName.add("Address");
			newList.add(newValue.getAddress());
			fieldName.add("Postal Code");
			newList.add(newValue.getPostalCode());
			fieldName.add("City");
			newList.add(newValue.getCity());
			fieldName.add("Country");
			newList.add(newValue.getCountry());
			calendar.setTime(newValue.getRegDate());
			String formatedNewRegDate = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
			fieldName.add("Register Date");
			newList.add(formatedNewRegDate);
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