package com.intprog.project.music.service;

public class MusicNotFoundException extends RuntimeException{
    public MusicNotFoundException (Long id){
        super(String.format("Music with id [%s] is not found", id));
    }
}
