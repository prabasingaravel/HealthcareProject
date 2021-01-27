package com.module.patientmodule.service;

import java.util.List;
import com.module.patientmodule.dto.PatientDetails;

public interface PatientService {
	
	public PatientDetails savePatientDetails(PatientDetails patient);
	
	public List<PatientDetails> getAllDetailsForPatient();
	
	public PatientDetails updatePatientDetail(PatientDetails patient);
	
	public PatientDetails getPatientDetailById(int patientId);
	
	public String deletePatientDetail(int patientId);
}
