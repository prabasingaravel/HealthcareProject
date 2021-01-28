package com.module.usermodule.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientVitalSignDto implements Serializable {
	
	private static final long serialVersionUID = 1069488061225213793L;
	private PatientDto patient;
	private VitalSignDto vitalsign;
}
