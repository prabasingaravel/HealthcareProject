package com.module.vitalsignmodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.module.vitalsignmodule.Model.VitalSign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VitalSignDto represent vital sign table.
 * @author Praba Singaravel
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignDto implements Serializable {
	private static final long serialVersionUID = 6440156593513661186L;
	private int patientId;
	private String userName;
	private Date checkupDate;
	private int pulse;
	private int bloodPressure;
	private float weight;
	private float temperature;
	private int bloodSugar;
	private int respirationRate;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	
	public static VitalSign ConvertVitalSignDomain(VitalSignDto vitalSignDto) {
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

	public static VitalSignDto ConvertVitalSignDto(VitalSign vitalSign) {
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
