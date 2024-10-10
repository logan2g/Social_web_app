package com.intprog.project.userProfile.controller;

import com.intprog.project.message.model.Message;
import com.intprog.project.music.model.Music;
import com.intprog.project.userProfile.model.UserProfile;
import com.intprog.project.video.model.Video;

import java.util.Set;

public class UserProfileDto {
    private Long Id;
    private String name;
    private String surname;
    private Set<Long> messages;
    private Set<Long> musicSet;
    private Set<Long> videoSet;

    public UserProfileDto() {
    }

    public UserProfileDto(UserProfile userProfile) {
        Id = userProfile.getId();
        name = userProfile.getName();
        surname = userProfile.getSurname();
        if(userProfile.getMessages() != null)
        for (Message mes : userProfile.getMessages()){
            messages.add(mes.getId());
        }
        if(userProfile.getMusicSet() != null)
        for(Music music : userProfile.getMusicSet()){
            musicSet.add(music.getId());
        }
        if(userProfile.getVideoSet() != null)
        for(Video video : userProfile.getVideoSet()){
            videoSet.add(video.getId());
        }
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Long> getMessages() {
        return messages;
    }

    public void setMessages(Set<Long> messages) {
        this.messages = messages;
    }

    public Set<Long> getMusicSet() {
        return musicSet;
    }

    public void setMusicSet(Set<Long> musicSet) {
        this.musicSet = musicSet;
    }

    public Set<Long> getVideoSet() {
        return videoSet;
    }

    public void setVideoSet(Set<Long> videoSet) {
        this.videoSet = videoSet;
    }
}
