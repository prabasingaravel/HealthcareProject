package com.module.patientmodule.Service;

import java.util.List;

import com.module.patientmodule.Dto.PatientVitalSignDto;

public interface PatientService {
	
	public PatientVitalSignDto addPatient(PatientVitalSignDto patient);
	
	public List<PatientVitalSignDto> getAllPatient();
	
	public PatientVitalSignDto updatePatient(PatientVitalSignDto patient);
	
	public PatientVitalSignDto getPatientById(int patientId);
	
	public String deletePatient(int patientId);
}
