package com.module.healthcareaudit.Service;

import java.text.ParseException;
import java.util.List;

import com.module.healthcareaudit.Model.Audit;

/**
 * AuditService is used for audit table CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public interface AuditService {

	/**
	 * doFilter method is used to fetch the audit detail based on date and service name
	 * @param fromDate
	 * @param toDate
	 * @param serviceName
	 * @return List<Audit>
	 */
	List<Audit> doFilter(String fromDate, String toDate, String serviceName) throws ParseException;

	/**
	 * findRequestType method is used to fetch the request type.
	 * @param request
	 * @return String
	 */
	String findRequestType(String request);

	/**
	 * findAction method is used to fetch the action performed.
	 * @param action
	 * @param serviceName
	 * @return String
	 */
	String findAction(String action, String serviceName);

	/**
	 * getAllAudit method is used to fetch the audit details
	 * @return List<Audit>
	 */
	List<Audit> getAllAudit();

}
