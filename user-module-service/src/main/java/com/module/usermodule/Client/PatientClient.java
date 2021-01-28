package com.module.usermodule.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.module.usermodule.Dto.PatientVitalSignDto;

@FeignClient(name="${healthcare.module.patient.name}",url="${healthcare.module.patient.uri}")
public interface PatientClient {
	
	@PostMapping(path="/entry",consumes= {"application/json"})
	public PatientVitalSignDto addPatient(@RequestBody PatientVitalSignDto patient);
	
	@GetMapping(path="/detail/{patientId}",produces= {"application/json"})
	public PatientVitalSignDto getPatientById(@PathVariable int patientId);

}
