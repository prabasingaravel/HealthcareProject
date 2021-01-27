package com.module.vitalsignmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDetail {
	private int patientId;
	private String patientName;
	private int age;
}
