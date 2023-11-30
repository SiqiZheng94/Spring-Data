package com.example.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class AsterixControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private AsterixRepo repo;
    @Autowired
    private ObjectMapper objectMapper;
    private String base_url="/asterix/characters";

    @Test
    void getAllCharacter_returnEmptyList_whenRepositoryIsEmpty() throws Exception {
        //GIVEN

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get(base_url))
        //THEN
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
    //Blackbox Test
    @Test
    void getBy_blackBoxTest() throws Exception {
        //GIVEN
        NewCharacterDTO newCharacterDTO = new NewCharacterDTO("name", 100, "test");
        String characterJson = objectMapper.writeValueAsString(newCharacterDTO);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(base_url + "/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(characterJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Character savedCharacter = objectMapper.readValue(result.getResponse().getContentAsString(), Character.class);
        String savedCharacterAsJson = objectMapper.writeValueAsString(savedCharacter);


        //WHEN
        mvc.perform(MockMvcRequestBuilders.get(base_url+"/search?id="+savedCharacter.id()))
        //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(savedCharacterAsJson));

    }

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