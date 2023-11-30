package com.example.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsterixRepo extends MongoRepository<Character,String> {
    Optional<Character> findByName(String name);
    List<Character> findAllByName(String name);
}
