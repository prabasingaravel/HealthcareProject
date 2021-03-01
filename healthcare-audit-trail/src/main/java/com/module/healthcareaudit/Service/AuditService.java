package com.module.healthcareaudit.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.module.healthcareaudit.Model.Audit;

public interface AuditService {

	List<Audit> doFilter(String fromDate, String toDate, String serviceName) throws ParseException;

	String findRequestType(String request);

	String findAction(String action, String serviceName);

	List<Audit> getAllAudit();

}
