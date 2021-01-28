package com.module.patientmodule.Dto;

import java.io.Serializable;

import com.module.patientmodule.Model.Patient;
import com.module.patientmodule.Model.VitalSign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientVitalSignDto implements Serializable {
	private static final long serialVersionUID = -1295019118314251665L;
	private Patient patient;
	private VitalSign vitalsign;
}
