package com.module.patientmodule.Controller;

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

import com.module.patientmodule.Advice.TrackExecutionTime;
import com.module.patientmodule.Advice.TrackLogging;
import com.module.patientmodule.Dto.PatientVitalSignDto;
import com.module.patientmodule.Service.PatientService;

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
	
	@ApiOperation(value = "Insert Patient Detail", response = PatientVitalSignDto.class)
	@PostMapping(path="/entry",consumes= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	public PatientVitalSignDto addPatient(@RequestBody PatientVitalSignDto patient) {
		return patientService.addPatient(patient);
	}
	
	@ApiOperation(value = "Fetch Patient Detail", response = Iterable.class)
	@GetMapping(path="/detail",produces= {"application/json"})
	@Cacheable(value = "patient")
	@TrackExecutionTime
	@TrackLogging
	public List<PatientVitalSignDto> getAllPatient() {
		return patientService.getAllPatient();
	}
	
	@ApiOperation(value = "Fetch a specific Patient Detail", response = PatientVitalSignDto.class)
	@GetMapping(path="/detail/{patientId}",produces= {"application/json"})
	@Cacheable(value = "patient", key = "#patientId")
	public PatientVitalSignDto getPatientById(@PathVariable int patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@ApiOperation(value = "Update Patient Detail", response = PatientVitalSignDto.class)
	@PutMapping(path="/update",consumes= {"application/json"})
	@CachePut(value = "patient")
	@TrackExecutionTime
	@TrackLogging
	public PatientVitalSignDto updatePatient(@RequestBody PatientVitalSignDto patient) {
		return patientService.updatePatient(patient);
	}
	
	@ApiOperation(value = "Delete Patient Detail", response = String.class)
	@DeleteMapping(path="/delete/{patientId}")
	@CacheEvict(value = "patient", key = "#patientId")
	public String deletePatient(@PathVariable int patientId) {
		return patientService.deletePatient(patientId);
	}

}
