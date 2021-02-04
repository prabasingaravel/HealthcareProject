package com.module.vitalsignmodule.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VitalSign which represent entity for vital sign table.
 * @author Praba Singaravel
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vital_sign")
@EntityListeners(AuditingEntityListener.class)
public class VitalSign implements Serializable {

	private static final long serialVersionUID = 6313577177732488030L;

	@Id
	@Column(name = "patient_id")
	private int patientId;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "checkup_date")
	private Date checkupDate;
	
	@Column(name = "pulse_rate")
	private int pulse;
	
	@Column(name = "blood_pressure")
	private int bloodPressure;
	
	@Column(name = "weight")
	private float weight;
	
	@Column(name = "body_temperature")
	private float temperature;
	
	@Column(name = "blood_sugar")
	private int bloodSugar;
	
	@Column(name = "respiration_rate")
	private int respirationRate;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
