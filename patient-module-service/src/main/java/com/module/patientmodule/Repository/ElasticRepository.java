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

	PatientIndex findByPatientId(int patientId);

}
