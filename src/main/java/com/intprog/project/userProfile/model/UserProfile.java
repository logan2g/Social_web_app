package com.intprog.project.userProfile.model;

import com.intprog.project.message.model.Message;
import com.intprog.project.music.model.Music;
import com.intprog.project.video.model.Video;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "User name can't be null or empty")
    private String name;
    @NotBlank(message = "User surname can't be null or empty")
    private String surname;
    @ManyToOne(targetEntity = Message.class)
    private Set<Message> messages;
    @ManyToMany(targetEntity = Music.class)
    @JoinColumn(name="musicId")
    private Set<Music> musicSet;
    @ManyToMany(targetEntity = Video.class)
    @JoinColumn(name="videoId")
    private Set<Video> videoSet;

    public UserProfile(){ }

    public UserProfile(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return Id;
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Music> getMusicSet() {
        return musicSet;
    }

    public void setMusicSet(Set<Music> musicSet) {
        this.musicSet = musicSet;
    }

    public Set<Video> getVideoSet() {
        return videoSet;
    }

    public void setVideoSet(Set<Video> videoSet) {
        this.videoSet = videoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile profile = (UserProfile) o;
        return Objects.equals(Id, profile.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
