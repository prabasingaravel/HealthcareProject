package com.module.vitalsignmodule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientVitalSignDto {
	
	private PatientDto patient;
	private VitalSignDto vitalsign;
}
