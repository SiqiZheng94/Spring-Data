package com.example.mongodb;

import ch.qos.logback.classic.spi.EventArgUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {
    AsterixRepo asterixRepo = mock(AsterixRepo.class);
    IdService idService = mock(IdService.class);
    AsterixService asterixService=new AsterixService(asterixRepo,idService);


    @Test
    void getAllCharacter_whenCalled_thenReturnAllCharacters() {
        //GIVEN
        List<Character> characters = List.of(new Character("1", "11", 111, "1111"));
        when(asterixRepo.findAll()).thenReturn(characters);
        //WHEN
        List<Character> actual = asterixService.getAllCharacter();
        //THEN
        List<Character> expected= characters;
        verify(asterixRepo).findAll();
        assertEquals(actual,expected);
    }
    @Test
    void findByid_whenId1_thenReturnCharacter1(){
        Character character1 = new Character("1", "11", 111, "1111");
        when(asterixRepo.findById("1")).thenReturn(Optional.of(character1));

        Character actual = asterixService.findById("1");

        Character expected = character1;
        verify(asterixRepo).findById("1");
        assertEquals(actual,expected);
        //strict test:the methode of my mock was called only once,
//and no other methods was called
        verify(asterixRepo,times(1)).findById("1");
        verifyNoMoreInteractions(asterixRepo);
    }
    @Test
    void updateCharacterDTO_whenId1_thenCharacterDTO1(){
        NewCharacterDTO characterDTO = new NewCharacterDTO("11", 111, "1111");
        Character character1 = new Character("1", "11", 111, "1111");
        when(asterixRepo.findById("1")).thenReturn(Optional.of(character1));
        when(asterixRepo.save(character1)).thenReturn(character1);

        Character actual = asterixService.updateCharacterDTO("1",characterDTO);

        Character expected = character1;
        verify(asterixRepo).findById("1");
        verify(asterixRepo).save(character1);
        assertEquals(expected,actual);
//strict test:the methode of my mock was called only once,
//and no other methods was called
        verify(asterixRepo,times(1)).findById("1");

        verify(asterixRepo,times(1)).save(character1);
        verifyNoMoreInteractions(asterixRepo);
    }
    @Test
    void deleteCharacter_whenId2_thenSuccessful(){

        asterixService.deleteCharacter("2");
        verify(asterixRepo).deleteById("2");
//strict test:the methode of my mock was called only once,
//and no other methods was called
        verify(asterixRepo,times(1)).deleteById("2");
        verifyNoMoreInteractions(asterixRepo);
    }
    @Test
    void saveCharacter_whenCharacterDTO_thenCharacter1(){
        NewCharacterDTO characterDTO = new NewCharacterDTO("a", 100, "Muster");

        Character character1 = new Character("1", characterDTO.name(), characterDTO.age(),characterDTO.occupation());
        when(asterixRepo.save(character1)).thenReturn(character1);
        when(idService.randomId()).thenReturn("1");


        Character actual= asterixService.saveCharacter(characterDTO);
        Character expected = new Character("1","a", 100, "Muster");

        verify(asterixRepo).save(character1);
        verify(idService).randomId();
        assertEquals(actual,expected);
//strict test:the methode of my mock was called only once,
//and no other methods was called
        verify(asterixRepo,times(1)).save(character1);
        verifyNoMoreInteractions(asterixRepo);
        verify(idService,times(1)).randomId();
        verifyNoMoreInteractions(idService);
    }

}