package com.isaac.searchcharacters.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrClientConfig {

    private static final String SOLR_URL = "http://localhost:8983/solr/anime_characters";

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(SOLR_URL).build();
    }
}
