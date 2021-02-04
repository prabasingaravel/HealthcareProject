package com.module.patientmodule.ServiceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.patientmodule.Advice.TrackExecutionTime;
import com.module.patientmodule.Advice.TrackLogging;
import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.ExceptionHandling.ResourceNotFoundException;
import com.module.patientmodule.Model.Patient;
import com.module.patientmodule.Repository.PatientRepository;
import com.module.patientmodule.Service.PatientService;
import com.module.patientmodule.Util.QueryUtil;

/**
 * PatientServiceImpl which implements PatientService.
 * @author Praba Singaravel
 *
 */
@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository, EntityManagerFactory entityManager) {
		this.patientRepository = patientRepository;
		this.entityManager = entityManager;
	}
	private final EntityManagerFactory entityManager;
	private final PatientRepository patientRepository;
	
	public PatientDto addPatient(PatientDto patientDto) {
		return PatientDto.ConvertPatientDto(patientRepository.save(PatientDto.ConvertPatientDomain(patientDto)));
	}
	
	@TrackExecutionTime
	public List<PatientDto> getAllPatient(){
		QueryUtil queryUtil = new QueryUtil();
		EntityManager manager = entityManager.createEntityManager();
		Query query = manager.createQuery(queryUtil.createQueryForAll());
		return query.getResultList();
	}
	
	public PatientDto updatePatient(PatientDto patientDto){
		Patient patient = patientRepository.getPatientById(patientDto.getPatientId());
		if(patient != null) {
			return PatientDto.ConvertPatientDto(patientRepository.save(PatientDto.ConvertPatientDomain(patientDto)));
		}else {
			throw new ResourceNotFoundException("Patient Detail not found for the id " + patientDto.getPatientId());
		}
	}
	
	@TrackLogging
	public PatientDto getPatientById(int patientId){
		Patient patient = patientRepository.getPatientById(patientId);
		if(patient != null) {
			return PatientDto.ConvertPatientDto(patient);
		}else {
			throw new ResourceNotFoundException("Patient Detail not found for the id " + patientId);
		}
	}
	
	public String deletePatient(int patientId){
		Patient patient = patientRepository.getPatientById(patientId);
		if(patient != null) {
			patientRepository.delete(patient);
			return "Patient Detail is deleted with id " + patientId;
		}else {
			throw new ResourceNotFoundException("Patient Detail not found for the id " + patientId);
		}
	}
}
