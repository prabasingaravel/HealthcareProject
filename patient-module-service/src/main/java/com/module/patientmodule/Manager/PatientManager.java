package com.module.patientmodule.Manager;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.patientmodule.Advice.TrackExecutionTime;
import com.module.patientmodule.Dto.PatientVitalSignDto;
import com.module.patientmodule.Model.Patient;
import com.module.patientmodule.Model.VitalSign;
import com.module.patientmodule.Repository.PatientRepository;
import com.module.patientmodule.Repository.VitalSignRepository;
import com.module.patientmodule.Service.PatientService;
import com.module.patientmodule.Util.QueryUtil;

@Service
public class PatientManager implements PatientService{
	
	@Autowired
	public PatientManager(PatientRepository patientRepo, VitalSignRepository vitalSignRepo, EntityManagerFactory entityManager) {
		super();
		this.patientRepo = patientRepo;
		this.vitalSignRepo = vitalSignRepo;
		this.entityManager = entityManager;
	}
	private final EntityManagerFactory entityManager;
	private final PatientRepository patientRepo;
	private final VitalSignRepository vitalSignRepo;
	
	@Transactional
	public PatientVitalSignDto addPatient(PatientVitalSignDto patient) {
		Patient patientInfo = patient.getPatient();
		patientInfo = patientRepo.save(patientInfo);
		VitalSign vitalSigns = patient.getVitalsign();
		if(vitalSigns.getPatientId() == patientInfo.getPatientId()) {
			vitalSigns = vitalSignRepo.save(vitalSigns);
			return new PatientVitalSignDto(patientInfo,vitalSigns);
		}else {
			throw new RuntimeException("Patient id is mismatch " + vitalSigns.getPatientId());
		}
	}
	
	@TrackExecutionTime
	public List<PatientVitalSignDto> getAllPatient(){
		QueryUtil queryUtil = new QueryUtil();
		EntityManager manager = entityManager.createEntityManager();
		Query query = manager.createQuery(queryUtil.createQueryForAll());
		return query.getResultList();
	}
	
	@Transactional
	public PatientVitalSignDto updatePatient(PatientVitalSignDto patient){
		Patient patientInfo = patient.getPatient();
		patientInfo = patientRepo.save(patientInfo);
		VitalSign vitalSigns = patient.getVitalsign();
		if(vitalSigns.getPatientId() == patientInfo.getPatientId()) {
			vitalSigns = vitalSignRepo.save(vitalSigns);
			return new PatientVitalSignDto(patientInfo,vitalSigns);
		}else {
			throw new RuntimeException("Patient id is mismatch " + vitalSigns.getPatientId());
		}
	}
	
	public PatientVitalSignDto getPatientById(int patientId){
		Patient patientInfo = patientRepo.getPatientInfo(patientId);
		VitalSign vitalSigns = vitalSignRepo.getVitalSign(patientId);
		return new PatientVitalSignDto(patientInfo,vitalSigns);
	}
	
	@Transactional
	public String deletePatient(int patientId){
		Optional<Patient> patientInfo = patientRepo.findById(patientId);
		Optional<VitalSign> vitalSigns = vitalSignRepo.findById(patientId);
		if(patientInfo.isPresent()) {
			patientRepo.delete(patientInfo.get());
			vitalSignRepo.delete(vitalSigns.get());
			return "Patient Detail is deleted with id "+patientId;
		}else {
			throw new RuntimeException("Patient Detail not found for the id " + patientId);
		}
	}
}
