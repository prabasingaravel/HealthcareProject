package com.module.patientmodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.module.patientmodule.Model.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PatientDto represent patient table.
 * @author Praba Singaravel
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {
	private static final long serialVersionUID = -1295019118314251665L;
	private int patientId;
	private String patientFirstName;
	private String patientLastName;
	private Date dob;
	private int age;
	private String gender;
	private String maritalStatus;
	private long contactNo;
	private String emailId;
	private String address;
	private long postalCode;
	private String city;
	private String country;
	private Date regDate;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	
	public static Patient ConvertPatientDomain(PatientDto patientDto) {
		Patient patient = new Patient();
		patient.setPatientId(patientDto.getPatientId());
		patient.setPatientFirstName(patientDto.getPatientFirstName());
		patient.setPatientLastName(patientDto.getPatientLastName());
		patient.setDob(patientDto.getDob());
		patient.setAge(patientDto.getAge());
		patient.setGender(patientDto.getGender());
		patient.setMaritalStatus(patientDto.getMaritalStatus());
		patient.setContactNo(patientDto.getContactNo());
		patient.setEmailId(patientDto.getEmailId());
		patient.setAddress(patientDto.getAddress());
		patient.setPostalCode(patientDto.getPostalCode());
		patient.setCity(patientDto.getCity());
		patient.setCountry(patientDto.getCountry());
		patient.setRegDate(patientDto.getRegDate());
		patient.setCreatedBy(patientDto.getCreatedBy());
		patient.setUpdatedBy(patientDto.getUpdatedBy());
		patient.setCreatedAt(patientDto.getCreatedAt());
		patient.setUpdatedAt(patientDto.getUpdatedAt());
		return patient;
	}

	public static PatientDto ConvertPatientDto(Patient patient) {
		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId(patient.getPatientId());
		patientDto.setPatientFirstName(patient.getPatientFirstName());
		patientDto.setPatientLastName(patient.getPatientLastName());
		patientDto.setDob(patient.getDob());
		patientDto.setAge(patient.getAge());
		patientDto.setGender(patient.getGender());
		patientDto.setMaritalStatus(patient.getMaritalStatus());
		patient.setContactNo(patientDto.getContactNo());
		patientDto.setEmailId(patient.getEmailId());
		patientDto.setAddress(patient.getAddress());
		patientDto.setPostalCode(patient.getPostalCode());
		patientDto.setCity(patient.getCity());
		patientDto.setCountry(patient.getCountry());
		patientDto.setRegDate(patient.getRegDate());
		patientDto.setCreatedBy(patient.getCreatedBy());
		patientDto.setUpdatedBy(patient.getUpdatedBy());
		patientDto.setCreatedAt(patient.getCreatedAt());
		patientDto.setUpdatedAt(patient.getUpdatedAt());
		return patientDto;
	}
}
