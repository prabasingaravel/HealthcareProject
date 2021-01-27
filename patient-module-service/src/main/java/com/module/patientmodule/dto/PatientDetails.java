package com.module.patientmodule.dto;

import java.io.Serializable;

import com.module.patientmodule.entity.PatientEntity;
import com.module.patientmodule.entity.VitalSignEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDetails implements Serializable {
	private static final long serialVersionUID = -1295019118314251665L;
	private PatientEntity patientInfo;
	private VitalSignEntity vitalsign;
}
