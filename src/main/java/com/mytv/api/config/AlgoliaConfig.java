package com.mytv.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algolia.api.SearchClient;

import org.springframework.beans.factory.annotation.Value;


@Configuration
public class AlgoliaConfig {

    @Value("${algolia.application-id}")
    private String applicationId;

    @Value("${algolia.admin-api-key}")
    private String apiKey;
    
    
    @Bean
    public SearchClient searchClient() {
    	return new SearchClient(applicationId, apiKey);
    }

}