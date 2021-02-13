package com.module.patientmodule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.module.patientmodule.Model.Patient;

/**
 * PatientRepository is a repository for patient table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Repository
@EnableJpaRepositories
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	Patient getPatientById(int patientId);
	
}
