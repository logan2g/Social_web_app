package com.intprog.project.video.controller;

import com.intprog.project.video.model.Video;
import com.intprog.project.userProfile.model.UserProfile;
import java.util.HashSet;
import java.util.Set;

public class VideoDto {
    private Long Id;
    private String name;
    private Set<Long> Users = new HashSet<>();

    public VideoDto() {
    }

    public VideoDto(Video video) {
        Id = video.getId();
        name= video.getName();
        for (UserProfile user : video.getUserProfiles()) {
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

    public Set<Long> getUsers() {
        return Users;
    }

    public void setUsers(Set<Long> users) {
        Users = users;
    }
}
