package com.module.patientmodule.Service;

import java.util.List;

import com.module.patientmodule.Dto.PatientDto;

import io.swagger.annotations.ApiOperation;

/**
 * PatientService is used for patient table CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public interface PatientService {
	
	/**
	 * addPatient method is used to register patient.
	 * @param patientDto
	 * @return PatientDto
	 */
	public PatientDto addPatient(PatientDto patientDto);
	
	/**
	 * getAllPatient method is used to fetch all patient details.
	 * @return List of PatientDto
	 */
	public List<PatientDto> getAllPatient();
	
	/**
	 * updatePatient method is used to update patient detail.
	 * @param patientDto
	 * @return PatientDto
	 */
	public PatientDto updatePatient(PatientDto patientDto);
	
	/**
	 * getPatientById method is used to fetch patient detail based on patient id.
	 * @param patientId
	 * @return PatientDto
	 */
	public PatientDto getPatientById(int patientId);
	
	/**
	 * deletePatient method is used to delete patient detail based on patient id.
	 * @param patientId
	 * @return String
	 */
	public String deletePatient(int patientId);
}
