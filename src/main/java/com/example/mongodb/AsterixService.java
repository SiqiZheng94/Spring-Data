package com.example.mongodb;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AsterixService {
    private final AsterixRepo repo;
    private final IdService idService;
    public List<Character> getAllCharacter(){
        return repo.findAll();
    }
    public List<Character> getAllCharacterYoungerThan(int age){
        List<Character> allCharacters = repo.findAll();
        return allCharacters.stream()
                .filter(character -> character.age()<=age)
                .collect(Collectors.toList());

    }
    public Character findById(String id){
        return repo.findById(id)
                .orElseThrow(()->new RuntimeException("Not Found!"));
    }

    public Character saveCharacter(NewCharacterDTO characterDTO){
        Character character1 = new Character(idService.randomId(), characterDTO.name(), characterDTO.age(),characterDTO.occupation());
        return repo.save(character1);
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
