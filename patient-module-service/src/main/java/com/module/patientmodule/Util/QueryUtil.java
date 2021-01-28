package com.module.patientmodule.Util;

public class QueryUtil {
	
	public String createQueryForAll() {
		String query = "select p,v from Patient p,VitalSign v";
		return query;
	}
	
}
