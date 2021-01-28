package com.module.vitalsignmodule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSignDto {
	private int pulse;
	private String pulseRange;
	private int bloodPressure;
	private String bloodPressureRange;
	private float weight;
	private float temperature;
	private String temperatureRange;
	private int bloodSugar;
	private String bloodSugarRange;
	private int respirationRate;
	private String respirationRateRange;
}
