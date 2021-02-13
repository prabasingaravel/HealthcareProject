package com.module.patientmodule.Index;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.Model.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PatientIndex which represent document for patient Index.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="patientindex",shards=2)
public class PatientIndex {
	@Id
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
	
	public static PatientIndex ConvertPatientDomain(PatientDto patientDto) {
		if (patientDto == null) {
			return null;
		}
		PatientIndex patientIndex = new PatientIndex();
		patientIndex.setPatientId(patientDto.getPatientId());
		patientIndex.setPatientFirstName(patientDto.getPatientFirstName());
		patientIndex.setPatientLastName(patientDto.getPatientLastName());
		patientIndex.setDob(patientDto.getDob());
		patientIndex.setAge(patientDto.getAge());
		patientIndex.setGender(patientDto.getGender());
		patientIndex.setMaritalStatus(patientDto.getMaritalStatus());
		patientIndex.setContactNo(patientDto.getContactNo());
		patientIndex.setEmailId(patientDto.getEmailId());
		patientIndex.setAddress(patientDto.getAddress());
		patientIndex.setPostalCode(patientDto.getPostalCode());
		patientIndex.setCity(patientDto.getCity());
		patientIndex.setCountry(patientDto.getCountry());
		patientIndex.setRegDate(patientDto.getRegDate());
		return patientIndex;
	}
	
	public static PatientIndex ConvertPatientElastic(Patient patient) {
		if (patient == null) {
			return null;
		}
		PatientIndex patientIndex = new PatientIndex();
		patientIndex.setPatientId(patient.getPatientId());
		patientIndex.setPatientFirstName(patient.getPatientFirstName());
		patientIndex.setPatientLastName(patient.getPatientLastName());
		patientIndex.setDob(patient.getDob());
		patientIndex.setAge(patient.getAge());
		patientIndex.setGender(patient.getGender());
		patientIndex.setMaritalStatus(patient.getMaritalStatus());
		patientIndex.setContactNo(patient.getContactNo());
		patientIndex.setEmailId(patient.getEmailId());
		patientIndex.setAddress(patient.getAddress());
		patientIndex.setPostalCode(patient.getPostalCode());
		patientIndex.setCity(patient.getCity());
		patientIndex.setCountry(patient.getCountry());
		patientIndex.setRegDate(patient.getRegDate());
		return patientIndex;
	}
}
