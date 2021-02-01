package com.module.usermodule.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.usermodule.Client.PatientClient;
import com.module.usermodule.Dto.PatientVitalSignDto;

@Service
public class PatientService {
	@Autowired
	public PatientService(PatientClient patientClient) {
		super();
		this.patientClient = patientClient;
	}

	private final PatientClient patientClient;

	public PatientVitalSignDto addPatient(PatientVitalSignDto patientDto) {
		return patientClient.addPatient(patientDto);
	}
	
	public PatientVitalSignDto getPatientById(int patientId) {
		 return patientClient.getPatientById(patientId);
	}

}
