package com.module.healthcareaudit.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.module.healthcareaudit.Model.Audit;

@Repository
public interface AuditRepository extends MongoRepository<Audit, String> {

	List<Audit> findByServiceName(String serviceName);
}
