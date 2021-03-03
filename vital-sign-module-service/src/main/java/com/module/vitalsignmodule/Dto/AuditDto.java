package com.module.vitalsignmodule.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuditDto represent audit table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
	
	private String id;
	
	private String userName;

	private String serviceName;

	private String request;

	private String action;
	
	private DataDto data;

	private String logDate;

	private String logTime;

}
