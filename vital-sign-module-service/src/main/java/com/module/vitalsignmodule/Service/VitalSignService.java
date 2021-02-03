package com.module.vitalsignmodule.Service;

import java.util.Date;

import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;

/**
 * VitalSignService is used for vitalsign table CRUD operation.
 * @author Praba Singaravel
 *
 */
public interface VitalSignService {
	
	public VitalSignDto addVitalSign(VitalSignDto vitalSignDto);
	
	public VitalSignDto updateVitalSign(VitalSignDto vitalSignDto);
	
	public VitalSignDto getVitalSignById(int patientId, Date checkupDate);
	
	public PatientDto getPatientById(int patient);
	
	public String deleteVitalSign(int patientId, Date checkupDate);
	
}
 