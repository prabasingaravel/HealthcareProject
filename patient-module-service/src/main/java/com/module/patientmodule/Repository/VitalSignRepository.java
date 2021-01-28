package com.module.patientmodule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.module.patientmodule.Model.VitalSign;

@Repository
@EnableJpaRepositories
public interface VitalSignRepository extends JpaRepository<VitalSign, Integer>{

	@Query(value = "select * from vitalsign v where v.patient_id = ?1",nativeQuery = true)
	VitalSign getVitalSign(int patientId);
}
