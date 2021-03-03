package com.module.healthcareaudit.Model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.module.healthcareaudit.Dto.DataDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Audit which represent document for audit table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Audit")
public class Audit implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	private String userName;

	private String serviceName;

	private String request;

	private String action;
	
	private DataDto data;

	private String logDate;

	private String logTime;

}
