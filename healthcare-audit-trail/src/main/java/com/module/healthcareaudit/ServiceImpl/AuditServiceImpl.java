package com.module.healthcareaudit.ServiceImpl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.module.healthcareaudit.Model.Audit;
import com.module.healthcareaudit.Repository.AuditRepository;
import com.module.healthcareaudit.Service.AuditService;

@Service
public class AuditServiceImpl implements AuditService{

	private final AuditRepository auditRepository;
	
	public AuditServiceImpl(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}
	
	@Override
	public List<Audit> doFilter(String fromDate, String toDate, String serviceName) throws ParseException {
		List<Audit> result_all = auditRepository.findByServiceName(serviceName);
		String dateStr1 = fromDate;
		String dateStr2 = toDate;
		dateStr1 = dateStr1.replace("-", "/");
		dateStr2 = dateStr2.replace("-", "/");
		Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr1);
		Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr2);
		List<Audit> result = new ArrayList<>();
		for (int i = 0; i < result_all.size(); i++) {
			result_all.get(i).getLogDate().setHours(00);
			result_all.get(i).getLogDate().setMinutes(00);
			if ((date1.equals(result_all.get(i).getLogDate()))
					| (date1.before(result_all.get(i).getLogDate()) && date2.after(result_all.get(i).getLogDate()))
					| (date2.equals(result_all.get(i).getLogDate()))) {
				result.add(result_all.get(i));
			}
		}
		return result;
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

}
