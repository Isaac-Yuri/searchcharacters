package com.isaac.searchcharacters.model;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@SolrDocument(collection = "characters") 
public class Character {

    @Id
    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String anime;

    @Field
    private String description;

}
