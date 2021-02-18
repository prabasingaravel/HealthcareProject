package com.module.vitalsignmodule.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.vitalsignmodule.Model.VitalSign;

/**
 * VitalSignRepository is a repository for vitalsign table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Repository
public interface VitalSignRepository extends JpaRepository<VitalSign, Integer> {

	/**
	 * findByPatientIdAndCheckupDate method used to fetch the vital sign detail based on patient id and checkup Date.
	 * @param patientId
	 * @param checkupDate
	 * @return VitalSign
	 */
	VitalSign findByPatientIdAndCheckupDate(int patientId, Date checkupDate);
	
}
