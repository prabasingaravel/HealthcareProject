package com.module.healthcareaudit.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.module.healthcareaudit.Model.Audit;
import com.module.healthcareaudit.Repository.AuditRepository;
import com.module.healthcareaudit.Service.AuditService;

/**
 * AuditServiceImpl which implements AuditService.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Service
public class AuditServiceImpl implements AuditService{

	private final AuditRepository auditRepository;
	
	public AuditServiceImpl(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}
	
	@Override
	public List<Audit> doFilter(String fromDate, String toDate, String serviceName) throws ParseException {
		List<Audit> result_all = auditRepository.findByServiceName(serviceName);
		return result_all;
	}

	@Override
	public String findRequestType(String request) {
		String result = "";
		if (request.startsWith("update")) {
			result = "UPDATE";
		}else if (request.startsWith("add")) {
			result = "CREATE";
		}
		return result;
	}

	@Override
	public String findAction(String action, String ServiceName) {
		String result = "";
		if (ServiceName.startsWith("User")) {
			if (action.equals("CREATE")) {
				result = "Created user detail";
			}else if (action.equals("UPDATE")) {
				result = "Updated user detail";
			}
		}else if (ServiceName.startsWith("Patient")) {
			if (action.equals("CREATE")) {
				result = "Created patient detail";
			}else if (action.equals("UPDATE")) {
				result = "Updated patient detail";
			}
		}else {
			if (action.equals("CREATE")) {
				result = "Created vital sign detail";
			}else if (action.equals("UPDATE")) {
				result = "Updated vital sign detail";
			}
		}
		return result;
	}

	@Override
	public List<Audit> getAllAudit() {
		return auditRepository.findAll();
	}

	@Override
	public List<Audit> getAuditByServiceName(String serviceName) {
		return auditRepository.findByServiceName(serviceName);
	}

}
