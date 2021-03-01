package com.module.healthcareaudit.Util;

import com.module.healthcareaudit.Dto.AuditDto;
import com.module.healthcareaudit.Model.Audit;

public class AuditConverter {

	/**
	 * convertToLaptopDto method is used to convert laptop entity to laptop dto.
	 * @param laptop
	 * @return LaptopDto
	 */
	public static AuditDto convertToAuditDto(Audit audit) {
		if (audit == null) {
			return null;
		}
		AuditDto auditDto = new AuditDto();
		auditDto.setId(audit.getId());
		auditDto.setUserName(audit.getUserName());
		auditDto.setServiceName(audit.getServiceName());
		auditDto.setRequest(audit.getRequest());
		auditDto.setAction(audit.getAction());
		auditDto.setOldValue(audit.getOldValue());
		auditDto.setNewValue(audit.getNewValue());
		auditDto.setLogDate(audit.getLogDate());
		auditDto.setLogTime(audit.getLogTime());
		return auditDto;
	}
	
	/**
	 * convertToLaptopEntity method is used to convert laptop Dto to laptop entity.
	 * @param laptopDto
	 * @return Laptop
	 */
	public static Audit convertToAuditEntity(AuditDto auditDto) {
		if (auditDto == null) {
			return null;
		}
		Audit audit = new Audit();
		audit.setId(auditDto.getId());
		audit.setUserName(auditDto.getUserName());
		audit.setServiceName(auditDto.getServiceName());
		audit.setRequest(auditDto.getRequest());
		audit.setAction(auditDto.getAction());
		audit.setOldValue(auditDto.getOldValue());
		audit.setNewValue(auditDto.getNewValue());
		audit.setLogDate(auditDto.getLogDate());
		audit.setLogTime(auditDto.getLogTime());
		return audit;
	}
}