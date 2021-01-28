package com.module.patientmodule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.module.patientmodule.Model.Patient;

@Repository
@EnableJpaRepositories
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	Patient getPatientInfo(int patientId);

}
