package com.intprog.project.video.model;

import com.intprog.project.userProfile.model.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Music name can't be null or empty")
    private String name;
    @ManyToMany(targetEntity = UserProfile.class, mappedBy="videoSet", fetch=FetchType.EAGER)
    private Set<UserProfile> UserProfiles;

    public Video(String name) {
        this.name = name;
    }

    public Video() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserProfile> getUserProfiles() {
        return UserProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        UserProfiles = userProfiles;
    }

    public Long getId() {
        return Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(Id, video.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return name;
    }
}
