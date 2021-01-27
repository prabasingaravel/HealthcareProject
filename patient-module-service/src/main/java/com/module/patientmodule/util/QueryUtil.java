package com.module.patientmodule.util;

public class QueryUtil {
	
	public String createQueryForAll() {
		String query = "select p,v from PatientEntity p,VitalSignEntity v";
		return query;
	}
	
}
