package com.module.patientmodule.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Vitalsign")
public class VitalSign implements Serializable {
	private static final long serialVersionUID = 4044663304464799476L;
	@Id
	@Column(name="patient_id")
	private int patientId;
	@Column(name="pulse")
	private int pulse;
	@Column(name="blood_pressure")
	private int bloodPressure;
	@Column(name="weight")
	private float weight;
	@Column(name="body_temperature")
	private float temperature;
	@Column(name="blood_sugar")
	private int bloodSugar;
	@Column(name="respiration_rate")
	private int respirationRate;
}
