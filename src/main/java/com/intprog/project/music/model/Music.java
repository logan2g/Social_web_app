package com.intprog.project.music.model;

import com.intprog.project.userProfile.model.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Music name can't be null or empty")
    private String name;
    @NotBlank(message = "Music author can't be null or empty")
    private String author;
    @ManyToMany(targetEntity = UserProfile.class, mappedBy="musicSet", fetch=FetchType.EAGER)
    private Set<UserProfile> UserProfiles;

    public Music(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Music() {
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Set<UserProfile> getUserProfiles() {
        return UserProfiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        UserProfiles = userProfiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music mus = (Music) o;
        return Objects.equals(Id, mus.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return author + " - " + name;
    }
}
