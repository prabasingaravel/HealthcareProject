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
public class VitalSignDto implements Serializable {
	private static final long serialVersionUID = -7011797563017773923L;
	private int patientId;
	private int pulse;
	private int bloodPressure;
	private float weight;
	private float temperature;
	private int bloodSugar;
	private int respirationRate;
}