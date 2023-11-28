package com.example.mongodb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc

class AsterixControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private AsterixRepo repo;
    private String base_url="/asterix/characters";

    @Test
    void getAllCharacter_returnEmptyList_whenRepositoryIsEmpty() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get(base_url))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }


    @Test
    void getBy_returnCharacter1_whenId1() throws Exception {
        NewCharacterDTO characterDTO = new NewCharacterDTO("test", 100, "Muster");
        Character character1 = new Character("1", characterDTO.name(), characterDTO.age(), characterDTO.occupation());
        Character save = repo.save(character1);
        //System.out.println(save);
        mvc.perform(MockMvcRequestBuilders.get(base_url+"/search?id=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
{
"id":"1",
"name":"test",
"age":100,
"occupation":"Muster"
}
"""));

    }
/*
    @Test
    void addCharacter_returnCharacter1_whenCalledWithCharacterDTO() {
        NewCharacterDTO characterDTO = new NewCharacterDTO("test", 100, "Muster");
        Character character1 = new Character("1", characterDTO.name(), characterDTO.age(), characterDTO.occupation());
        repo.save(character1);
        mvc.perform(MockMvcRequestBuilders.post())



    }

 */

    @Test
    void deleteCharacter() {
    }

    @Test
    void updateCharacter() {
    }

    @Test
    void updateCharacterDTO() {
    }
}