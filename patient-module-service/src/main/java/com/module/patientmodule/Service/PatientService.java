package com.module.patientmodule.Service;

import java.util.List;

import com.module.patientmodule.Dto.PatientDto;

/**
 * PatientService is used for patient table CRUD operation.
 * @author Praba Singaravel
 *
 */
public interface PatientService {
	
	public PatientDto addPatient(PatientDto patientDto);
	
	public List<PatientDto> getAllPatient();
	
	public PatientDto updatePatient(PatientDto patientDto);
	
	public PatientDto getPatientById(int patientId);
	
	public String deletePatient(int patientId);
}
