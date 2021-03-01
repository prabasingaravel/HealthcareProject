package com.module.healthcareaudit.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.healthcareaudit.Model.Audit;
import com.module.healthcareaudit.Service.AuditService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/audits")
public class AuditController {

	private final AuditService auditService;

	@Autowired
	public AuditController(AuditService auditService) {
		this.auditService = auditService;
	}
	
	@GetMapping("/")
	public List<Audit> getAllAudit() {
		return auditService.getAllAudit();
	}
	
	@GetMapping("/{fromDate}/{toDate}/{serviceName}")
	public List<Audit> getUserAudit(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String fromDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") String toDate,
			@PathVariable String serviceName) throws ParseException {
		return auditService.doFilter(fromDate, toDate, serviceName);
	}
}