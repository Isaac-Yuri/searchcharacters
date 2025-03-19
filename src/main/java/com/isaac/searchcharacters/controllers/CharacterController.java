package com.isaac.searchcharacters.controllers;

import com.isaac.searchcharacters.model.Character;
import com.isaac.searchcharacters.service.CharacterService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public void addCharacter(@RequestBody Character character) throws SolrServerException, IOException {
        characterService.saveCharacter(character);
    }

    @GetMapping
    public List<Character> getAllCharacters() throws SolrServerException, IOException {
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable String id) throws SolrServerException, IOException {
        return characterService.getCharacterById(id);
    }

    @GetMapping("/search")
    public List<Character> searchByName(@RequestParam String name) throws SolrServerException, IOException {
        return characterService.getCharactersByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) throws SolrServerException, IOException {
        characterService.deleteCharacter(id);
    }
}
