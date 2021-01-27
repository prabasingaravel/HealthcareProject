package com.module.vitalsignmodule.service;

import com.module.vitalsignmodule.dto.PatientVitalSigns;

public interface VitalSignService {
	
	public PatientVitalSigns getPatientVitalSign(int patientId);
	
}
