package com.module.vitalsignmodule.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.vitalsignmodule.Model.VitalSign;

/**
 * VitalSignRepository is a repository for vitalsign table.
 * @author Praba Singaravel
 *
 */
@Repository
public interface VitalSignRepository extends JpaRepository<VitalSign, Integer> {

	VitalSign findByPatientIdAndCheckupDate(int patientId, Date checkupDate);
	
}
