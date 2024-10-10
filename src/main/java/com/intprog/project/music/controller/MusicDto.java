package com.intprog.project.music.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intprog.project.message.model.Message;
import com.intprog.project.music.model.Music;
import com.intprog.project.userProfile.model.UserProfile;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class MusicDto {
    private Long Id;
    private String name;
    private String author;
    private Set<Long> Users = new HashSet<>();

    public MusicDto() {
    }

    public MusicDto(Music music) {
        Id = music.getId();
        name= music.getName();
        author = music.getAuthor();
        for (UserProfile user : music.getUserProfiles()) {
            Users.add(user.getId());
        };
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Long> getUsers() {
        return Users;
    }

    public void setUsers(Set<Long> users) {
        Users = users;
    }
}
