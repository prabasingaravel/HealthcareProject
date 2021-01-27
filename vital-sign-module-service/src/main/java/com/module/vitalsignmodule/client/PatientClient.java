package com.module.vitalsignmodule.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.module.vitalsignmodule.dto.PatientVitalSigns;

@FeignClient(name="${healthcare.module.patient.name}",url="${healthcare.module.patient.uri}")
public interface PatientClient {
	
	@GetMapping(path="/detail/{patientId}",produces= {"application/json"})
	public PatientVitalSigns getPatientDetailById(@PathVariable int patientId);
}
