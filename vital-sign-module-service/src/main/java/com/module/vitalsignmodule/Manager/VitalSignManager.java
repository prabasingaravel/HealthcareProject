package com.module.vitalsignmodule.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.vitalsignmodule.Client.PatientClient;
import com.module.vitalsignmodule.Config.RangeConfiguration;
import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.PatientVitalSignDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Service.VitalSignService;

@Service
public class VitalSignManager implements VitalSignService {
	
	@Autowired
	public VitalSignManager(PatientClient patientClient, RangeConfiguration configuration) {
		super();
		this.patientClient = patientClient;
		this.configuration = configuration;
	}
	
	private final PatientClient patientClient;
	private RangeConfiguration configuration;
	
	public PatientVitalSignDto getPatientVitalSign(int patientId) {
		PatientVitalSignDto response = patientClient.getPatientDetailById(patientId);
		if(response != null) {
			PatientDto patientDetail = new PatientDto(response.getPatient().getPatientId(),
					response.getPatient().getPatientName(),response.getPatient().getAge());
			VitalSignDto vitalSigns = new VitalSignDto(response.getVitalsign().getPulse(),configuration.getPulseRange(),
					response.getVitalsign().getBloodPressure(),configuration.getBloodPressureRange(),
					response.getVitalsign().getWeight(),response.getVitalsign().getTemperature(),configuration.getTemperatureRange(),
					response.getVitalsign().getBloodSugar(),configuration.getBloodSugarRange(),
					response.getVitalsign().getRespirationRate(),configuration.getRespirationRateRange());
			return new PatientVitalSignDto(patientDetail,vitalSigns);
		} else {
			throw new RuntimeException("Patient Detail not found for the id " + patientId);
		}
	}
}
