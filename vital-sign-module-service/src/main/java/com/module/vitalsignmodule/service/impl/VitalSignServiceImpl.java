package com.module.vitalsignmodule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.module.vitalsignmodule.client.PatientClient;
import com.module.vitalsignmodule.config.RangeConfiguration;
import com.module.vitalsignmodule.dto.PatientDetail;
import com.module.vitalsignmodule.dto.PatientVitalSigns;
import com.module.vitalsignmodule.dto.VitalSign;
import com.module.vitalsignmodule.service.VitalSignService;

@Service
public class VitalSignServiceImpl implements VitalSignService {
	
	@Autowired
	public VitalSignServiceImpl(PatientClient patientClient, RangeConfiguration configuration) {
		super();
		this.patientClient = patientClient;
		this.configuration = configuration;
	}
	
	private final PatientClient patientClient;
	private RangeConfiguration configuration;
	
	public PatientVitalSigns getPatientVitalSign(int patientId) {
		PatientVitalSigns response = patientClient.getPatientDetailById(patientId);
		if(response != null) {
			PatientDetail patientDetail = new PatientDetail(response.getPatientInfo().getPatientId(),
					response.getPatientInfo().getPatientName(),response.getPatientInfo().getAge());
			VitalSign vitalSigns = new VitalSign(response.getVitalsign().getPulse(),configuration.getPulseRange(),
					response.getVitalsign().getBloodPressure(),configuration.getBloodPressureRange(),
					response.getVitalsign().getWeight(),response.getVitalsign().getTemperature(),configuration.getTemperatureRange(),
					response.getVitalsign().getBloodSugar(),configuration.getBloodSugarRange(),
					response.getVitalsign().getRespirationRate(),configuration.getRespirationRateRange());
			return new PatientVitalSigns(patientDetail,vitalSigns);
		} else {
			throw new RuntimeException("Patient Detail not found for the id " + patientId);
		}
	}
}
