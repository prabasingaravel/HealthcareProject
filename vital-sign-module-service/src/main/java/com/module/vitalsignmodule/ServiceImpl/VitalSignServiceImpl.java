package com.module.vitalsignmodule.ServiceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.vitalsignmodule.Client.PatientClient;
import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.ExceptionHandling.ResourceNotFoundException;
import com.module.vitalsignmodule.Model.VitalSign;
import com.module.vitalsignmodule.Repository.VitalSignRepository;
import com.module.vitalsignmodule.Service.VitalSignService;

/**
 * VitalSignServiceImpl which implements VitalSignService.
 * @author Praba Singaravel
 *
 */
@Service
public class VitalSignServiceImpl implements VitalSignService {
	
	@Autowired
	public VitalSignServiceImpl(VitalSignRepository vitalSignRepository, PatientClient patientClient) {
		this.vitalSignRepository = vitalSignRepository;
		this.patientClient = patientClient;
	}
	
	private final VitalSignRepository vitalSignRepository;
	private final PatientClient patientClient;

	public VitalSignDto addVitalSign(VitalSignDto vitalSignDto) {
		return VitalSignDto.ConvertVitalSignDto(vitalSignRepository.save(VitalSignDto.ConvertVitalSignDomain(vitalSignDto)));
	}

	public VitalSignDto updateVitalSign(VitalSignDto vitalSignDto) {
		VitalSign vitalSign = vitalSignRepository.findByPatientIdAndCheckupDate(vitalSignDto.getPatientId(), vitalSignDto.getCheckupDate());
		if(vitalSign != null) {
			return VitalSignDto.ConvertVitalSignDto(vitalSignRepository.save(VitalSignDto.ConvertVitalSignDomain(vitalSignDto)));
		}else {
			throw new ResourceNotFoundException("Vital Sign not found for the id " + vitalSignDto.getPatientId());
		}
	}

	public VitalSignDto getVitalSignById(int patientId, Date checkupDate) {
		VitalSign vitalSign = vitalSignRepository.findByPatientIdAndCheckupDate(patientId,checkupDate);
		if(vitalSign != null) {
			return VitalSignDto.ConvertVitalSignDto(vitalSign);
		}else {
			throw new ResourceNotFoundException("Vital Sign not found for the id " + patientId);
		}
	}
	
	public PatientDto getPatientById(int patientId) {
		return patientClient.getPatientById(patientId);
	}

	public String deleteVitalSign(int patientId,Date checkupDate) {
		VitalSign vitalSign = vitalSignRepository.findByPatientIdAndCheckupDate(patientId,checkupDate);
		if(vitalSign != null) {
			vitalSignRepository.delete(vitalSign);
			return "Vital Sign is deleted with id " + patientId;
		}else {
			throw new ResourceNotFoundException("Vital Sign not found for the id " + patientId);
		}
	}

}
