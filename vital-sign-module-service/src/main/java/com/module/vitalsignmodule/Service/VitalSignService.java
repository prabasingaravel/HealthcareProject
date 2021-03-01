package com.module.vitalsignmodule.Service;

import java.text.ParseException;
import java.util.Date;

import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;

/**
 * VitalSignService is used for vitalsign table CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public interface VitalSignService {
	
	/**
	 * addVitalSign method is used to register vital sign.
	 * @param vitalSignDto
	 * @return VitalSignDto
	 */
	public VitalSignDto addVitalSign(VitalSignDto vitalSignDto);
	
	/**
	 * updateVitalSign method is used to update vital sign detail.
	 * @param vitalSignDto
	 * @return VitalSignDto
	 * @throws ParseException 
	 */
	public VitalSignDto updateVitalSign(int patientId, Date checkupDate, VitalSignDto vitalSignDto) throws ParseException;
	
	/**
	 * getVitalSignById method used to fetch the vital sign detail based on patient id and checkup Date.
	 * @param patientId
	 * @param checkupDate
	 * @return VitalSign
	 */
	public VitalSignDto getVitalSignById(int patientId, Date checkupDate);
	
	/**
	 * getPatientById method is used to get the patient information based on patient id.
	 * @param patientId
	 * @return PatientDto
	 */
	public PatientDto getPatientById(int patient);
	
	/**
	 * deleteVitalSign method is used to delete vital sign detail based on patient id.
	 * @param patientId
	 * @param checkupDate
	 * @return String
	 */
	public String deleteVitalSign(int patientId, Date checkupDate);
	
}
 