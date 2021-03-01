package com.module.healthcareaudit.Model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Audit")
public class Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
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
