package com.example.mongodb;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/asterix/characters")
@RequiredArgsConstructor
public class AsterixController {
    /*
    @GetMapping
    public List<Character> getAllCharacters(){
        return List.of(
                new Character("1", "Asterix", 35, "Krieger"),
                new Character("2", "Obelix", 35, "Lieferant"),
                new Character("3", "Miraculix", 60, "Druide"),
                new Character("4", "Majestix", 60, "Häuptling"),
                new Character("5", "Troubadix", 25, "Barden"),
                new Character("6", "Gutemine", 35, "Häuptlingsfrau"),
                new Character("7", "Idefix", 5, "Hund"),
                new Character("8", "Geriatrix", 70, "Rentner"),
                new Character("9", "Automatix", 35, "Schmied"),
                new Character("10", "Grockelix", 35, "Fischer")
        );
    }

     */
    private final AsterixService service;
    @GetMapping
    public List<Character> getAllCharacter(){

        return service.getAllCharacter();
    }
    @GetMapping("/young")
    public List<Character> getAllCharacterYoungerThan(@RequestParam int age){

        return service.getAllCharacterYoungerThan(age);
    }
    @GetMapping("/search")
    public Character getBy(@RequestParam String id){
        return service.findById(id);
    }

    @PostMapping("/add")
    public Character addCharacter(@RequestBody NewCharacterDTO characterDTO){
        return service.saveCharacter(characterDTO);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteCharacter(@PathVariable String id){
        service.deleteCharacter(id);
    }


    @PutMapping("/update/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody String name){
        return service.updateCharacter(id,name);
    }
    @PutMapping("/updateDTO/{id}")
    public Character updateCharacterDTO(@PathVariable String id, @RequestBody NewCharacterDTO characterDTO){
        return service.updateCharacterDTO(id,characterDTO);
    }
}

