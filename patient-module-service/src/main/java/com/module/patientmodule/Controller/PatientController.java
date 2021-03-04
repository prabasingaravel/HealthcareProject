package com.module.patientmodule.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.patientmodule.Advice.AuditTrailLogging;
import com.module.patientmodule.Advice.TrackExecutionTime;
import com.module.patientmodule.Advice.TrackLogging;
import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.Service.PatientService;

import io.swagger.annotations.ApiOperation;

/**
 * PatientController is used for patients end point.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/patients")
public class PatientController {
	
	private final PatientService patientService;
	
	@Lazy
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	/**
	 * addPatient method is used to register patient.
	 * @param patientDto
	 * @return PatientDto
	 */
	@ApiOperation(value = "Insert Patient Detail", response = PatientDto.class)
	@PostMapping(path="/",consumes= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	@AuditTrailLogging
	public PatientDto addPatient(@RequestBody PatientDto patientDto) {
		return patientService.addPatient(patientDto);
	}
	
	/**
	 * getAllPatient method is used to fetch all patient details.
	 * @return List of PatientDto
	 */
	@ApiOperation(value = "Fetch Patient Detail", response = Iterable.class)
	@GetMapping(path="/",produces= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	public List<PatientDto> getAllPatient() {
		return patientService.getAllPatient();
	}
	
	/**
	 * getPatientById method is used to fetch patient detail based on patient id.
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
	@TrackExecutionTime
	@TrackLogging
	@AuditTrailLogging
	public PatientDto updatePatient(@RequestBody PatientDto patientDto) {
		return patientService.updatePatient(patientDto);
	}
	
	/**
	 * deletePatient method is used to delete patient detail based on patient id.
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
