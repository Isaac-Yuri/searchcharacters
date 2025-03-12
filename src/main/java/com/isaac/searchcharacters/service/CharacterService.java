package com.isaac.searchcharacters.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.isaac.searchcharacters.model.Character;
import com.isaac.searchcharacters.repository.CharacterRepository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository repository;

    public List<Character> getAll() {
        return (List<Character>) repository.findAll();
    }

    public Optional<Character> getById(String id) {
        return repository.findById(id);
    }

    public List<Character> searchByName(String name) {
        return repository.findByNameContaining(name);
    }

    public Character save(Character character) {
        Duration duration = Duration.ofSeconds(1);
        return repository.save(character, duration);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
