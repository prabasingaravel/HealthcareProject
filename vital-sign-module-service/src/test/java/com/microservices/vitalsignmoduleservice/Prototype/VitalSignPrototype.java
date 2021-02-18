package com.microservices.vitalsignmoduleservice.Prototype;

import java.sql.Date;

import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Model.VitalSign;

public class VitalSignPrototype {

	public static VitalSign vitalSign() {
		VitalSign vitalSign = new VitalSign();
		vitalSign.setPatientId(12);
		vitalSign.setUserName("Sanju");
		vitalSign.setCheckupDate(Date.valueOf("2021-02-16"));
		vitalSign.setPulse(75);
		vitalSign.setBloodPressure(90);
		vitalSign.setWeight(55);
		vitalSign.setTemperature(95);
		vitalSign.setBloodSugar(100);
		vitalSign.setRespirationRate(15);
		return vitalSign;
	}
	
	public static VitalSignDto vitalSignDto() {
		VitalSignDto vitalSign = new VitalSignDto();
		vitalSign.setPatientId(12);
		vitalSign.setUserName("Sanju");
		vitalSign.setCheckupDate(Date.valueOf("2021-02-16"));
		vitalSign.setPulse(75);
		vitalSign.setBloodPressure(90);
		vitalSign.setWeight(55);
		vitalSign.setTemperature(95);
		vitalSign.setBloodSugar(100);
		vitalSign.setRespirationRate(15);
		return vitalSign;
	}
	
	public static PatientDto patientDto() {
		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId(12);
		patientDto.setPatientFirstName("Ravi");
		patientDto.setPatientLastName("Shankar");
		patientDto.setDob(Date.valueOf("1994-11-29"));
		patientDto.setAge(27);
		patientDto.setGender("Male");
		patientDto.setMaritalStatus("Married");
		patientDto.setContactNo(Long.valueOf("9892299319"));
		patientDto.setEmailId("ravishankar@gmail.com");
		patientDto.setAddress("sks street");
		patientDto.setPostalCode(636014);
		patientDto.setCity("Salem");
		patientDto.setCountry("India");
		patientDto.setRegDate(Date.valueOf("2021-02-16"));
		return patientDto;
	}
}
