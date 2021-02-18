package com.module.vitalsignmodule.ServiceImpl;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.module.vitalsignmodule.Client.PatientClient;
import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.ExceptionHandling.ResourceNotFoundException;
import com.module.vitalsignmodule.Model.VitalSign;
import com.module.vitalsignmodule.Repository.VitalSignRepository;
import com.module.vitalsignmodule.Service.VitalSignService;
import com.module.vitalsignmodule.Util.VitalSignConverter;

/**
 * VitalSignServiceImpl which implements VitalSignService.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Service
public class VitalSignServiceImpl implements VitalSignService {
	
	@Lazy
	@Autowired
	public VitalSignServiceImpl(VitalSignRepository vitalSignRepository, PatientClient patientClient) {
		this.vitalSignRepository = vitalSignRepository;
		this.patientClient = patientClient;
	}
	
	private final VitalSignRepository vitalSignRepository;
	private final PatientClient patientClient;

	public VitalSignDto addVitalSign(VitalSignDto vitalSignDto) {
		return VitalSignConverter.convertToVitalSignDto(vitalSignRepository.save(VitalSignConverter.convertToVitalSignEntity(vitalSignDto)));
	}

	public VitalSignDto updateVitalSign(VitalSignDto vitalSignDto) {
		VitalSign vitalSign = vitalSignRepository.findByPatientIdAndCheckupDate(vitalSignDto.getPatientId(), vitalSignDto.getCheckupDate());
		if(Objects.nonNull(vitalSign)) {
			return VitalSignConverter.convertToVitalSignDto(vitalSignRepository.save(VitalSignConverter.convertToVitalSignEntity(vitalSignDto)));
		}else {
			throw new ResourceNotFoundException("Vital Sign not found for the id " + vitalSignDto.getPatientId());
		}
	}

	public VitalSignDto getVitalSignById(int patientId, Date checkupDate) {
		VitalSign vitalSign = vitalSignRepository.findByPatientIdAndCheckupDate(patientId,checkupDate);
		if(Objects.nonNull(vitalSign)) {
			return VitalSignConverter.convertToVitalSignDto(vitalSign);
		}else {
			throw new ResourceNotFoundException("Vital Sign not found for the id " + patientId);
		}
	}
	
	public PatientDto getPatientById(int patientId) {
		return patientClient.getPatientById(patientId);
	}

	public String deleteVitalSign(int patientId,Date checkupDate) {
		VitalSign vitalSign = vitalSignRepository.findByPatientIdAndCheckupDate(patientId,checkupDate);
		if(Objects.nonNull(vitalSign)) {
			vitalSignRepository.delete(vitalSign);
			return "Vital Sign is deleted with id " + patientId;
		}else {
			throw new ResourceNotFoundException("Vital Sign not found for the id " + patientId);
		}
	}

}
