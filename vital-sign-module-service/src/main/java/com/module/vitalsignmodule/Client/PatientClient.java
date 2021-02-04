package com.module.vitalsignmodule.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.module.vitalsignmodule.Dto.PatientDto;

@FeignClient(name="${healthcare.module.patient.name}",url="${healthcare.module.patient.uri}")
public interface PatientClient {
	
	@GetMapping(path="/{patientId}",produces= {"application/json"})
	public PatientDto getPatientById(@PathVariable int patientId);
}