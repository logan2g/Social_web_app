package com.intprog.project.message.model;

import com.intprog.project.userProfile.model.UserProfile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Message text can't be null or empty")
    private String text;
    @ManyToOne
    @JoinColumn(name="userProfileId")
    private UserProfile UserProfile;

    public Message(String text, UserProfile userProfile) {
        this.text = text;
        UserProfile = userProfile;
    }

    public Message() {
    }

    public Long getId() {
        return Id;
    }

    public String getText() {
        return text;
    }

    public UserProfile getUserProfile() {
        return UserProfile;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserProfile(UserProfile userProfile) {
        UserProfile = userProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message mes = (Message) o;
        return Objects.equals(Id, mes.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        return text;
    }
}
