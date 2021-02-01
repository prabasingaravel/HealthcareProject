package com.module.usermodule.Dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto implements Serializable {
	private static final long serialVersionUID = 2111629752975981089L;
	private int patientId;
	private String patientName;
	private int age;
	private String gender;
	private Date regDate;
	private String city;
	private String maritalStatus;
	private BigInteger contactNo;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updateAt;
}
