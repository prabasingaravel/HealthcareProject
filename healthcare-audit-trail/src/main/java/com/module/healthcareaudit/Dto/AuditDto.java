package com.module.healthcareaudit.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
	
	private String id;
	
	private String userName;

	private String serviceName;

	private String request;

	private String action;
	
	private Object oldValue;
	
	private Object newValue;

	private Date logDate;

	private String logTime;

}
