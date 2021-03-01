package com.module.patientmodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PatientDto represent patient table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto implements Serializable {
	private static final long serialVersionUID = -1295019118314251665L;
	private int patientId;
	private String patientFirstName;
	private String patientLastName;
	private Date dob;
	private int age;
	private String gender;
	private String maritalStatus;
	private long contactNo;
	private String emailId;
	private String address;
	private long postalCode;
	private String city;
	private String country;
	private Date regDate;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
}
