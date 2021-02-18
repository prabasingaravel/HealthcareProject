package com.module.patientmodule.Repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.module.patientmodule.Index.PatientIndex;

/**
 * ElasticRepository is a repository for patient table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Repository
public interface ElasticRepository extends ElasticsearchRepository<PatientIndex, Integer>{

	/**
	 * findByPatientId method is used to fetch patient detail based on patient id.
	 * @param patientId
	 * @return PatientIndex
	 */
	PatientIndex findByPatientId(int patientId);

}
