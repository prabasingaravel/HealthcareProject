package com.module.vitalsignmodule.Controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.vitalsignmodule.Advice.AuditTrailLogging;
import com.module.vitalsignmodule.Advice.TrackExecutionTime;
import com.module.vitalsignmodule.Advice.TrackLogging;
import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Service.VitalSignService;

/**
 * VitalSignController is used for Vital Sign end point.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/vitalsigns")
public class VitalSignController {

	private final VitalSignService vitalSignSerivce;
	
	@Lazy
	@Autowired
	public VitalSignController(VitalSignService vitalSignSerivce) {
		this.vitalSignSerivce = vitalSignSerivce;
	}
	
	/**
	 * addVitalSign method is used to register vital sign.
	 * @param vitalSignDto
	 * @return VitalSignDto
	 */
	@PostMapping(path="/",consumes= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	@AuditTrailLogging
	public VitalSignDto addVitalSign(@RequestBody VitalSignDto vitalSignDto) {
		return vitalSignSerivce.addVitalSign(vitalSignDto);
	}
	
	/**
	 * getVitalSignById method used to fetch the vital sign detail based on patient id and checkup Date.
	 * @param patientId
	 * @param checkupDate
	 * @return VitalSign
	 */
	@GetMapping(path="/{patientId}/{checkupDate}",produces= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	public VitalSignDto getVitalSignById(@PathVariable int patientId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkupDate) {
		return vitalSignSerivce.getVitalSignById(patientId,checkupDate);
	}
	
	/**
	 * getPatientById method is used to get the patient information based on patient id.
	 * @param patientId
	 * @return PatientDto
	 */
	@GetMapping(path="/{patientId}",produces= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	public PatientDto getPatientById(@PathVariable int patientId) {
		return vitalSignSerivce.getPatientById(patientId);
	}
	
	/**
	 * updateVitalSign method is used to update vital sign detail.
	 * @param patientId
	 * @param checkupDate
	 * @param vitalSignDto
	 * @return VitalSignDto
	 * @throws ParseException 
	 */
	@PutMapping(path="/{patientId}/{checkupDate}/",consumes= {"application/json"})
	@TrackExecutionTime
	@TrackLogging
	@AuditTrailLogging
	public VitalSignDto updateVitalSign(@PathVariable int patientId, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkupDate,
			@RequestBody VitalSignDto vitalSignDto) throws ParseException {
		return vitalSignSerivce.updateVitalSign(patientId, checkupDate, vitalSignDto);
	}
	
	/**
	 * deleteVitalSign method is used to delete vital sign detail based on patient id.
	 * @param patientId
	 * @param checkupDate
	 * @return String
	 */
	@DeleteMapping(path="/{patientId}/{checkupDate}")
	@TrackExecutionTime
	@TrackLogging
	@CacheEvict(value = "vitalsign", key="#patientId")
	public String deleteVitalSign(@PathVariable int patientId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkupDate) {
		return vitalSignSerivce.deleteVitalSign(patientId,checkupDate);
	}
}
