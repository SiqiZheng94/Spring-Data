package com.example.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Character updateCharacter(String id, String name) {
        Optional<Character> findC=repo.findById(id);
        if(findC.isPresent()){
            return repo.save(findC.get().withName(name));
        }
        throw new RuntimeException("Not found!");
    }
}
