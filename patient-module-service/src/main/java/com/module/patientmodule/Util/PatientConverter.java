package com.module.patientmodule.Util;

import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.Model.Patient;

/**
 * PatientConverter is used to convert entity to dto and dto to entity.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public class PatientConverter {

	/**
	 * convertToPatientEntity method is used to convert patient Dto to patient entity.
	 * @param patientDto
	 * @return Patient
	 */
	public static Patient convertToPatientEntity(PatientDto patientDto) {
		if (patientDto == null) {
			return null;
		}
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

	/**
	 * convertToPatientDto method is used to convert patient entity to patient dto.
	 * @param patient
	 * @return PatientDto
	 */
	public static PatientDto convertToPatientDto(Patient patient) {
		if (patient == null) {
			return null;
		}
		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId(patient.getPatientId());
		patientDto.setPatientFirstName(patient.getPatientFirstName());
		patientDto.setPatientLastName(patient.getPatientLastName());
		patientDto.setDob(patient.getDob());
		patientDto.setAge(patient.getAge());
		patientDto.setGender(patient.getGender());
		patientDto.setMaritalStatus(patient.getMaritalStatus());
		patientDto.setContactNo(patientDto.getContactNo());
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
