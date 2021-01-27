package com.module.patientmodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.patientmodule.dto.PatientDetails;
import com.module.patientmodule.service.PatientService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	private final PatientService patientService;
	
	@ApiOperation(value = "Insert Patient Detail", response = PatientDetails.class)
	@PostMapping(path="/entry",consumes= {"application/json"})
	public PatientDetails addPatient(@RequestBody PatientDetails patient) {
		return patientService.savePatientDetails(patient);
	}
	
	@ApiOperation(value = "Fetch Patient Detail", response = Iterable.class)
	@GetMapping(path="/detail",produces= {"application/json"})
	@Cacheable(value = "patient")
	public List<PatientDetails> getAllPatientDetails() {
		return patientService.getAllDetailsForPatient();
	}
	
	@ApiOperation(value = "Fetch a specific Patient Detail", response = PatientDetails.class)
	@GetMapping(path="/detail/{patientId}",produces= {"application/json"})
	@Cacheable(value = "patient", key = "#patientId")
	public PatientDetails getPatientDetailById(@PathVariable int patientId) {
		return patientService.getPatientDetailById(patientId);
	}
	
	@ApiOperation(value = "Update Patient Detail", response = PatientDetails.class)
	@PutMapping(path="/update",consumes= {"application/json"})
	@CachePut(value = "patient")
	public PatientDetails updatePatientDetail(@RequestBody PatientDetails patient) {
		return patientService.updatePatientDetail(patient);
	}
	
	@ApiOperation(value = "Delete Patient Detail", response = String.class)
	@DeleteMapping(path="/delete/{patientId}")
	@CacheEvict(value = "patient", key = "#patientId")
	public String deletePatientDetail(@PathVariable int patientId) {
		return patientService.deletePatientDetail(patientId);
	}
}
