package com.module.patientmodule.Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name="Patient.getPatientInfo",query="select p from Patient p where p.patientId = ?1")
@Table(name="Patient")
public class Patient implements Serializable {
	private static final long serialVersionUID = -701824271052903076L;
	@Id
	@Column(name="patient_id")
	@ApiModelProperty(notes = "ID of the patient", name = "patientId", required = true)
	private int patientId;
	@Column(name="patient_name")
	@ApiModelProperty(notes = "Name of the patient", name = "patientName", required = true)
	private String patientName;
	@Column(name="age")
	@ApiModelProperty(notes = "Age of the patient", name = "age", required = true)
	private int age;
	@Column(name="gender")
	@ApiModelProperty(notes = "Gender of the patient", name = "gender", required = true)
	private String gender;
	@Column(name="admission_date")
	@ApiModelProperty(notes = "Registration date", name = "regDate", required = true)
	private Date regDate;
	@Column(name="city")
	@ApiModelProperty(notes = "City of the patient", name = "city", required = true)
	private String city;
	@Column(name="marital_status")
	@ApiModelProperty(notes = "Marital status of the patient", name = "maritalStatus", required = true)
	private String maritalStatus;
	@Column(name="contact_no")
	@ApiModelProperty(notes = "Phone number of the patient", name = "contactNo", required = true)
	private BigInteger contactNo;
}
