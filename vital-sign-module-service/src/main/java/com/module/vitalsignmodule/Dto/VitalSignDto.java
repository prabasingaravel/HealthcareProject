package com.module.vitalsignmodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VitalSignDto represent vital sign table.
 * @author Praba Singaravel
 * @since 21.02
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
}
