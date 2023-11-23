package com.example.mongodb;

import lombok.With;

@With
public record Character(String id,String name,int age,String occupation) {
}
