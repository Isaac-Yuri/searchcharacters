package com.isaac.searchcharacters.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isaac.searchcharacters.service.CharacterService;
import com.isaac.searchcharacters.model.Character;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService service;

    @GetMapping
    public List<Character> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Character> search(@RequestParam String name) {
        return service.searchByName(name);
    }

    @PostMapping
    public Character create(@RequestBody Character character) {
        return service.save(character);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> update(@PathVariable String id, @RequestBody Character character) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        character.setId(id);
        return ResponseEntity.ok(service.save(character));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!service.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
