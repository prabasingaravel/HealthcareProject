package com.module.vitalsignmodule.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.module.vitalsignmodule.Config.PatientClientConfig;
import com.module.vitalsignmodule.Dto.PatientDto;

/**
 * PatientClient is used to communicate the patient service.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@FeignClient(name="${healthcare.module.patient.name}",url = "${healthcare.module.patient.uri}",configuration = PatientClientConfig.class)
public interface PatientClient {
	
	/**
	 * getPatientById method is used to fetch patient detail based on patient id.
	 * @param patientId
	 * @return PatientDto
	 */
//	@RequestLine("GET /{patientId}")
//	@Headers("Content-Type: application/json")
	@GetMapping(path="/{patientId}",produces= {"application/json"})
	public PatientDto getPatientById(@PathVariable int patientId);
}
