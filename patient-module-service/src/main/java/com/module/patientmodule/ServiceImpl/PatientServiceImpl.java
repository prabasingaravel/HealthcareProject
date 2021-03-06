package com.module.patientmodule.ServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.module.patientmodule.Dto.PatientDto;
import com.module.patientmodule.ExceptionHandling.ResourceNotFoundException;
import com.module.patientmodule.Index.PatientIndex;
import com.module.patientmodule.Model.Patient;
import com.module.patientmodule.Repository.ElasticRepository;
import com.module.patientmodule.Repository.PatientRepository;
import com.module.patientmodule.Service.PatientService;
import com.module.patientmodule.Util.PatientConverter;

/**
 * PatientServiceImpl which implements PatientService.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Service
public class PatientServiceImpl implements PatientService{
	
	private final EntityManagerFactory entityManager;
	private final PatientRepository patientRepository;
	private final ElasticRepository elasticRepository;
	
	@Lazy
	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository, EntityManagerFactory entityManager,
			ElasticRepository elasticRepository) {
		this.patientRepository = patientRepository;
		this.entityManager = entityManager;
		this.elasticRepository = elasticRepository;
	}
	
	@Override
	public PatientDto addPatient(PatientDto patientDto) {
		elasticRepository.save(PatientIndex.ConvertPatientDomain(patientDto));
		return PatientConverter.convertToPatientDto(patientRepository.save(PatientConverter.convertToPatientEntity(patientDto)));
	}
	
	@Override
	public List<PatientDto> getAllPatient(){
		return patientRepository.findAll().stream().map(patient -> PatientConverter.convertToPatientDto(patient))
				.collect(Collectors.toList());
	}
	
	@Override
	public PatientDto updatePatient(PatientDto patientDto){
		Patient patient = patientRepository.getPatientById(patientDto.getPatientId());
		if(Objects.nonNull(patient)) {
			elasticRepository.save(PatientIndex.ConvertPatientDomain(patientDto));
			return PatientConverter.convertToPatientDto(patientRepository.save(PatientConverter.convertToPatientEntity(patientDto)));
		}else {
			throw new ResourceNotFoundException("Patient Detail not found for the id " + patientDto.getPatientId());
		}
	}
	
	@Override
	public PatientDto getPatientById(int patientId){
		Patient patient = patientRepository.getPatientById(patientId);
		if(Objects.nonNull(patient)) {
			return PatientConverter.convertToPatientDto(patient);
		}else {
			throw new ResourceNotFoundException("Patient Detail not found for the id " + patientId);
		}
	}
	
	@Override
	public String deletePatient(int patientId){
		Patient patient = patientRepository.getPatientById(patientId);
		if(Objects.nonNull(patient)) {
			elasticRepository.delete(PatientIndex.ConvertPatientElastic(patient));
			patientRepository.delete(patient);
			return "Patient Detail is deleted with id " + patientId;
		}else {
			throw new ResourceNotFoundException("Patient Detail not found for the id " + patientId);
		}
	}
}
