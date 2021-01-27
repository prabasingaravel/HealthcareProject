package com.module.vitalsignmodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.vitalsignmodule.dto.PatientVitalSigns;
import com.module.vitalsignmodule.service.VitalSignService;

@RestController
@RequestMapping("/vitalsign")
public class VitalSignController {

	@Autowired
	public VitalSignController(VitalSignService vitalSignSerivce) {
		super();
		this.vitalSignSerivce = vitalSignSerivce;
	}
	private VitalSignService vitalSignSerivce;
	
	@GetMapping("/{patientId}")
	public PatientVitalSigns getPatientVitalSigns(@PathVariable int patientId) {
		return vitalSignSerivce.getPatientVitalSign(patientId);
	}
}
