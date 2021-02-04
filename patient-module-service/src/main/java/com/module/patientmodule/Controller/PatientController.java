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
import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.Service.PatientService;

import io.swagger.annotations.ApiOperation;

/**
 * PatientController is used for patients end point.
 * @author Praba Singaravel
 *
 */
@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	private final PatientService patientService;
	
	/**
	 * addPatient method is used to register patient.
	 * @param patientDto
	 * @return PatientDto
	 */
	@ApiOperation(value = "Insert Patient Detail", response = PatientDto.class)
	@PostMapping(path="/",consumes= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	public PatientDto addPatient(@RequestBody PatientDto patientDto) {
		return patientService.addPatient(patientDto);
	}
	
	/**
	 * getAllPatient method is used to fetch all patient details.
	 * @return List
	 */
	@ApiOperation(value = "Fetch Patient Detail", response = Iterable.class)
	@GetMapping(path="/",produces= {"application/json"})
	@Cacheable(value = "patient")
	@TrackExecutionTime
	@TrackLogging
	public List<PatientDto> getAllPatient() {
		return patientService.getAllPatient();
	}
	
	/**
	 * getPatientById method is used to get specific patient detail.
	 * @param patientId
	 * @return PatientDto
	 */
	@ApiOperation(value = "Fetch a specific Patient Detail", response = PatientDto.class)
	@GetMapping(path="/{patientId}",produces= {"application/json"})
	@Cacheable(value = "patient", key = "#patientId")
	public PatientDto getPatientById(@PathVariable int patientId) {
		return patientService.getPatientById(patientId);
	}
	
	/**
	 * updatePatient method is used to update patient detail.
	 * @param patientDto
	 * @return PatientDto
	 */
	@ApiOperation(value = "Update Patient Detail", response = PatientDto.class)
	@PutMapping(path="/",consumes= {"application/json"})
	@CachePut(value = "patient")
	@TrackExecutionTime
	@TrackLogging
	public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
		return patientService.updatePatient(patientDto);
	}
	
	/**
	 * deletePatient method is used to delete patient detail.
	 * @param patientId
	 * @return String
	 */
	@ApiOperation(value = "Delete Patient Detail", response = String.class)
	@DeleteMapping(path="/{patientId}")
	@CacheEvict(value = "patient", key = "#patientId")
	public String deletePatient(@PathVariable int patientId) {
		return patientService.deletePatient(patientId);
	}

}
