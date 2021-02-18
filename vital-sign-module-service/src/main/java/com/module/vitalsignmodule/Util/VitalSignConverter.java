package com.module.vitalsignmodule.Util;

import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Model.VitalSign;

/**
 * VitalSignConverter is used to convert entity to dto and dto to entity.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public class VitalSignConverter {
	
	/**
	 * convertToVitalSignEntity method is used to convert vital sign Dto to vital sign entity.
	 * @param vitalSignDto
	 * @return VitalSign
	 */
	public static VitalSign convertToVitalSignEntity(VitalSignDto vitalSignDto) {
		if (vitalSignDto == null) {
			return null;
		}
		VitalSign vitalSign = new VitalSign();
		vitalSign.setPatientId(vitalSignDto.getPatientId());
		vitalSign.setUserName(vitalSignDto.getUserName());
		vitalSign.setCheckupDate(vitalSignDto.getCheckupDate());
		vitalSign.setPulse(vitalSignDto.getPulse());
		vitalSign.setBloodPressure(vitalSignDto.getBloodPressure());
		vitalSign.setWeight(vitalSignDto.getWeight());
		vitalSign.setTemperature(vitalSignDto.getTemperature());
		vitalSign.setBloodSugar(vitalSignDto.getBloodSugar());
		vitalSign.setRespirationRate(vitalSignDto.getRespirationRate());
		vitalSign.setCreatedBy(vitalSignDto.getCreatedBy());
		vitalSign.setCreatedAt(vitalSignDto.getCreatedAt());
		vitalSign.setUpdatedBy(vitalSignDto.getUpdatedBy());
		vitalSign.setUpdatedAt(vitalSignDto.getUpdatedAt());
		return vitalSign;
	}

	/**
	 * convertToVitalSignDto method is used to convert vital sign entity to vital sign dto.
	 * @param vitalSign
	 * @return VitalSignDto
	 */
	public static VitalSignDto convertToVitalSignDto(VitalSign vitalSign) {
		if (vitalSign == null) {
			return null;
		}
		VitalSignDto vitalSignDto = new VitalSignDto();
		vitalSignDto.setPatientId(vitalSign.getPatientId());
		vitalSignDto.setUserName(vitalSign.getUserName());
		vitalSignDto.setCheckupDate(vitalSign.getCheckupDate());
		vitalSignDto.setPulse(vitalSign.getPulse());
		vitalSignDto.setBloodPressure(vitalSign.getBloodPressure());
		vitalSignDto.setWeight(vitalSign.getWeight());
		vitalSignDto.setTemperature(vitalSign.getTemperature());
		vitalSignDto.setBloodSugar(vitalSign.getBloodSugar());
		vitalSignDto.setRespirationRate(vitalSign.getRespirationRate());
		vitalSignDto.setCreatedBy(vitalSign.getCreatedBy());
		vitalSignDto.setCreatedAt(vitalSign.getCreatedAt());
		vitalSignDto.setUpdatedBy(vitalSign.getUpdatedBy());
		vitalSignDto.setUpdatedAt(vitalSign.getUpdatedAt());
		return vitalSignDto;
	}
}
