package com.module.patientmodule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.patientmodule.entity.VitalSignEntity;

@Repository
public interface VitalSignRepository extends JpaRepository<VitalSignEntity, Integer>{

	@Query(value = "select * from vital_sign v where v.patient_id = ?1",nativeQuery = true)
	VitalSignEntity getVitalSign(int patientId);
}
