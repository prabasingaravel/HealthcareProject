package com.module.patientmodule.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.patientmodule.aop.TrackExecutionTime;
import com.module.patientmodule.dao.PatientRepository;
import com.module.patientmodule.dao.VitalSignRepository;
import com.module.patientmodule.dto.PatientDetails;
import com.module.patientmodule.entity.PatientEntity;
import com.module.patientmodule.entity.VitalSignEntity;
import com.module.patientmodule.service.PatientService;
import com.module.patientmodule.util.QueryUtil;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	public PatientServiceImpl(PatientRepository patientRepo, VitalSignRepository vitalSignRepo, EntityManagerFactory entityManager) {
		super();
		this.patientRepo = patientRepo;
		this.vitalSignRepo = vitalSignRepo;
		this.entityManager = entityManager;
	}
	private final EntityManagerFactory entityManager;
	private final PatientRepository patientRepo;
	private final VitalSignRepository vitalSignRepo;
	
	@Transactional
	public PatientDetails savePatientDetails(PatientDetails patient) {
		PatientEntity patientInfo = patient.getPatientInfo();
		patientInfo = patientRepo.save(patientInfo);
		VitalSignEntity vitalSigns = patient.getVitalsign();
		if(vitalSigns.getPatientId() == patientInfo.getPatientId()) {
			vitalSigns = vitalSignRepo.save(vitalSigns);
			return new PatientDetails(patientInfo,vitalSigns);
		}else {
			throw new RuntimeException("Patient id is mismatch " + vitalSigns.getPatientId());
		}
	}
	
	@TrackExecutionTime
	public List<PatientDetails> getAllDetailsForPatient(){
		QueryUtil queryUtil = new QueryUtil();
		EntityManager manager = entityManager.createEntityManager();
		Query query = manager.createQuery(queryUtil.createQueryForAll());
		return query.getResultList();
	}
	
	@Transactional
	public PatientDetails updatePatientDetail(PatientDetails patient){
		PatientEntity patientInfo = patient.getPatientInfo();
		patientInfo = patientRepo.save(patientInfo);
		VitalSignEntity vitalSigns = patient.getVitalsign();
		if(vitalSigns.getPatientId() == patientInfo.getPatientId()) {
			vitalSigns = vitalSignRepo.save(vitalSigns);
			return new PatientDetails(patientInfo,vitalSigns);
		}else {
			throw new RuntimeException("Patient id is mismatch " + vitalSigns.getPatientId());
		}
	}
	
	public PatientDetails getPatientDetailById(int patientId){
		PatientEntity patientInfo = patientRepo.getPatientInfo(patientId);
		VitalSignEntity vitalSigns = vitalSignRepo.getVitalSign(patientId);
		return new PatientDetails(patientInfo,vitalSigns);
	}
	
	@Transactional
	public String deletePatientDetail(int patientId){
		Optional<PatientEntity> patientInfo = patientRepo.findById(patientId);
		Optional<VitalSignEntity> vitalSigns = vitalSignRepo.findById(patientId);
		if(patientInfo.isPresent()) {
			patientRepo.delete(patientInfo.get());
			vitalSignRepo.delete(vitalSigns.get());
			return "Patient Detail is deleted with id "+patientId;
		}else {
			throw new RuntimeException("Patient Detail not found for the id " + patientId);
		}
	}
}
