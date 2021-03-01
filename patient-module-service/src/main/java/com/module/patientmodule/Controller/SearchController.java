package com.module.patientmodule.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.patientmodule.Index.PatientIndex;
import com.module.patientmodule.Util.QueryDSLService;

/**
 * SearchController is used for search end point.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/searchpatients")
public class SearchController {

	private final QueryDSLService queryDSLService;
	
	@Lazy
	@Autowired
	public SearchController(QueryDSLService queryDSLService) {
		this.queryDSLService = queryDSLService;
	}
	
	/**
	 * searchByMultiField method is used to search the patient based on name and age.
	 * @param firstName
	 * @param age
	 * @return PatientIndex
	 */
	@GetMapping("/{firstName}/{age}")
	public SearchHits<PatientIndex> searchByMultiField(@PathVariable String firstName, @PathVariable int age){
		return queryDSLService.searchMultiField(firstName,age);
	}
	
	/**
	 * getEmployeeSearchData method is used to search the patient based on name.
	 * @param firstName
	 * @return PatientIndex
	 */
	@GetMapping("/{firstName}")
	public SearchHits<PatientIndex> getEmployeeSearchData(@PathVariable String firstName){
		return queryDSLService.getEmployeeSearchData(firstName);
	}
	
	/**
	 * doMultiMatchQuery method is used to search the patient based on first name or last name.
	 * @param text
	 * @return PatientIndex
	 */
	@GetMapping("/name/{text}")
	public SearchHits<PatientIndex> doMultiMatchQuery(@PathVariable String text){
		return queryDSLService.multiMatchQuery(text);
	}
}
