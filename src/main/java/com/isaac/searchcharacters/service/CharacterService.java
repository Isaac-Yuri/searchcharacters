package com.isaac.searchcharacters.service;

import com.isaac.searchcharacters.model.Character;
import com.isaac.searchcharacters.repository.CharacterRepository;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public void saveCharacter(Character character) throws SolrServerException, IOException {
        characterRepository.save(character);
    }

    public List<Character> getAllCharacters() throws SolrServerException, IOException {
        return characterRepository.findAll();
    }

    public Character getCharacterById(String id) throws SolrServerException, IOException {
        return characterRepository.findById(id);
    }

    public List<Character> getCharactersByName(String name) throws SolrServerException, IOException {
        return characterRepository.findByName(name);
    }

    public void deleteCharacter(String id) throws SolrServerException, IOException {
        characterRepository.deleteById(id);
    }
}
