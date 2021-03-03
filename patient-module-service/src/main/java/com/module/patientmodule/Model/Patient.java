package com.module.patientmodule.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
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
 * Patient which represent entity for patient table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name="Patient.getPatientById",query="select p from Patient p where p.patientId = ?1")
@Table(name="Patient")
@EntityListeners(AuditingEntityListener.class)
public class Patient implements Serializable {
	private static final long serialVersionUID = -701824271052903076L;
	@Id
	@Column(name="patient_id")
	private int patientId;
	
	@Column(name = "patient_firstname")
	private String patientFirstName;

	@Column(name = "patient_lastname")
	private String patientLastName;

	@Column(name = "patient_dob")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date dob;
	
	@Column(name="age")
	private int age;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "mobile_number")
	private long contactNo;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "address")
	private String address;

	@Column(name = "postal_code")
	private long postalCode;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;
	
	@Column(name="registration_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date regDate;
	
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
