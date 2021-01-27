package com.module.vitalsignmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientVitalSigns {
	
	private PatientDetail patientInfo;
	private VitalSign vitalsign;
}
