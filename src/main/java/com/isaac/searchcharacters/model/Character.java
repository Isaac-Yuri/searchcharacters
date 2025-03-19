package com.isaac.searchcharacters.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    private String id;
    private String name;
    private String anime;
    private String description;
}
