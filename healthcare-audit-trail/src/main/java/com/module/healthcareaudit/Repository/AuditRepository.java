package com.module.healthcareaudit.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.module.healthcareaudit.Model.Audit;

/**
 * AuditRepository is a repository for Audit table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Repository
public interface AuditRepository extends MongoRepository<Audit, String> {

	/**
	 * findByServiceName method is used to fetch Audit detail based on service name.
	 * @param serviceName
	 * @return List<Audit>
	 */
	List<Audit> findByServiceName(String serviceName);
}
