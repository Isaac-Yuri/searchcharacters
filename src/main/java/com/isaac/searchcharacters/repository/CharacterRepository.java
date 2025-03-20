package com.isaac.searchcharacters.repository;

import com.isaac.searchcharacters.model.Character;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterRepository {

    @Autowired
    private SolrClient solrClient;

    public void save(Character character) throws SolrServerException, IOException {
        character.generateId();

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", character.getId());
        doc.addField("name", character.getName());
        doc.addField("anime", character.getAnime());
        doc.addField("description", character.getDescription());

        solrClient.add(doc); 
        solrClient.commit();   
    }

    public List<Character> findAll() throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery("*:*");
        SolrDocumentList documents = solrClient.query(query).getResults();
        return convertDocumentsToCharacters(documents);
    }

    public Character findById(String id) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery("id:" + id);
        SolrDocumentList documents = solrClient.query(query).getResults();
        return documents.isEmpty() ? null : convertDocumentToCharacter(documents.get(0));
    }

    public List<Character> findByName(String name) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery("name:" + name);
        SolrDocumentList documents = solrClient.query(query).getResults();
        return convertDocumentsToCharacters(documents);
    }

    public void deleteById(String id) throws SolrServerException, IOException {
        solrClient.deleteById(id);
        solrClient.commit();
    }

    private List<Character> convertDocumentsToCharacters(SolrDocumentList documents) {
        List<Character> characters = new ArrayList<>();
        for (SolrDocument doc : documents) {
            characters.add(convertDocumentToCharacter(doc));
        }
        return characters;
    }

    public Character convertDocumentToCharacter(SolrDocument document) {
        Character character = new Character();

        if (document.getFieldValue("id") instanceof ArrayList) {
            ArrayList<String> names = (ArrayList<String>) document.getFieldValue("id");
            character.setId(names.get(0)); 
        } else {
            character.setId((String) document.getFieldValue("id"));
        }
        
        if (document.getFieldValue("name") instanceof ArrayList) {
            ArrayList<String> names = (ArrayList<String>) document.getFieldValue("name");
            character.setName(names.get(0)); 
        } else {
            character.setName((String) document.getFieldValue("name"));
        }
        
        if (document.getFieldValue("anime") instanceof ArrayList) {
            ArrayList<String> animes = (ArrayList<String>) document.getFieldValue("anime");
            character.setAnime(animes.get(0));
        } else {
            character.setAnime((String) document.getFieldValue("anime"));
        }
    
        if (document.getFieldValue("description") instanceof ArrayList) {
            ArrayList<String> descriptions = (ArrayList<String>) document.getFieldValue("description");
            character.setDescription(descriptions.get(0)); 
        } else {
            character.setDescription((String) document.getFieldValue("description"));
        }
    
        return character;
    }
}
