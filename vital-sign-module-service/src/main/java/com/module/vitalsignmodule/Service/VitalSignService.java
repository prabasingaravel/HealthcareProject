package com.module.vitalsignmodule.Service;

import com.module.vitalsignmodule.Dto.PatientVitalSignDto;

public interface VitalSignService {
	
	public PatientVitalSignDto getPatientVitalSign(int patientId);
	
}
