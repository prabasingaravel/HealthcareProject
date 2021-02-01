package com.module.vitalsignmodule.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
	private int patientId;
	private String patientName;
	private int age;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updateAt;
}
