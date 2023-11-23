package com.example.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final AsterixRepo repo;
    public List<Character> getAllCharacter(){
        return repo.findAll();
    }
    public Character findById(String id){
        return repo.findById(id)
                .orElseThrow(()->new RuntimeException("Not Found!"));
    }
    public Character saveCharacter(Character character){
        return repo.save(character);
    }
    public void deleteCharacter(String id){
        repo.deleteById(id);
    }

}
