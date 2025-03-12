package com.isaac.searchcharacters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@EnableSolrRepositories(basePackages = "com.isaac.searchcharacters.repository")
@SpringBootApplication
public class SearchcharactersApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchcharactersApplication.class, args);
	}
}
