package com.profile.profilematching.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.profile.profilematching.repository")
@ComponentScan(basePackages = "com.profile.profilematching")
public class Config extends AbstractElasticsearchConfiguration {
	
	@Value("${elasticsearch.url}")
	public String elasticsearchURL;

	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		
		final ClientConfiguration config = ClientConfiguration.builder().connectedTo(elasticsearchURL).build();
		return RestClients.create(config).rest();
	}


}
