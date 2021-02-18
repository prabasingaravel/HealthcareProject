package com.module.patientmodule.Config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * ElasticSearchConfig is used to generate the elastic search template.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Configuration
@EnableElasticsearchRepositories(basePackages="com.module.patientmodule.Repository")
@ComponentScan(basePackages = { "com.module.patientmodule.Util.QueryDSLService" })
public class ElasticSearchConfig {

	/**
	 * client is used to build the rest clients configuration.
	 * @return RestHighLevelClient
	 *
	 */
	@Bean
	public RestHighLevelClient client() {
		ClientConfiguration clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build();
		return RestClients.create(clientConfiguration).rest();
	}
	
	/**
	 * elasticsearchTemplate is used to generate elastic search rest template.
	 * @return ElasticsearchOperations
	 *
	 */
	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		return new ElasticsearchRestTemplate(client());
	}
}
