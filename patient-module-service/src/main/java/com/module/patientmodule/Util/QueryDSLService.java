package com.module.patientmodule.Util;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.module.patientmodule.Index.PatientIndex;

/**
 * QueryDSLService is used for patient table search operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Service
public class QueryDSLService {

	private final ElasticsearchOperations elasticsearchOperations;
	
	@Lazy
	@Autowired
	public QueryDSLService(ElasticsearchOperations elasticsearchOperations) {
		this.elasticsearchOperations = elasticsearchOperations;
	}
	
	/**
	 * searchByMultiField method is used to search the patient based on name and age.
	 * @param firstName
	 * @param age
	 * @return PatientIndex
	 */
	public SearchHits<PatientIndex> searchMultiField(String firstName, int age){
		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders
				.matchQuery("patientFirstName", firstName)).must(QueryBuilders.matchQuery("age", age));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
		SearchHits<PatientIndex> patient = elasticsearchOperations.search(nativeSearchQuery, PatientIndex.class);
		return patient;
	}
	
	/**
	 * getEmployeeSearchData method is used to search the patient based on name.
	 * @param firstName
	 * @return PatientIndex
	 */
	public SearchHits<PatientIndex> getEmployeeSearchData(String input){
		String search = ".*" + input + ".*";
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withFilter(QueryBuilders.regexpQuery("patientFirstName", search)).build();
		SearchHits<PatientIndex> patient = elasticsearchOperations.search(searchQuery, PatientIndex.class);
		return patient;
	}
	
	/**
	 * doMultiMatchQuery method is used to search the patient based on first name or last name.
	 * @param text
	 * @return PatientIndex
	 */
	public SearchHits<PatientIndex> multiMatchQuery(String text){
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.multiMatchQuery(text)).withFields("patientFirstName")
				.withFields("patientLastName").build();
		SearchHits<PatientIndex> patient = elasticsearchOperations.search(nativeSearchQuery, PatientIndex.class);
		return patient;
	}
}
