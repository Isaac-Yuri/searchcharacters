package com.isaac.searchcharacters.repository;

import java.util.List;
import org.springframework.data.solr.repository.SolrCrudRepository;
import com.isaac.searchcharacters.model.Character;

public interface CharacterRepository extends SolrCrudRepository<Character, String> {
    List<Character> findByNameContaining(String name);
}
