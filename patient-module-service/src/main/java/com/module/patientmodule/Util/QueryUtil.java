package com.module.patientmodule.Util;

/**
 * QueryUtil class used to generate customize query.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public class QueryUtil {
	
	/**
	 * createQueryForAll method is used to create query for fetching all patient details.
	 * @return String
	 */
	public String createQueryForAll() {
		return "select p from Patient p";
	}
	
}
