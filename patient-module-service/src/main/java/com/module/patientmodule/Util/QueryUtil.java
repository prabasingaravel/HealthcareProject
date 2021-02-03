package com.module.patientmodule.Util;

/**
 * QueryUtil class used to generate customize query.
 * @author Praba Singaravel
 *
 */
public class QueryUtil {
	
	public String createQueryForAll() {
		String query = "select p from Patient p";
		return query;
	}
	
}
