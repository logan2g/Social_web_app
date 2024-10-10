package com.intprog.project.message.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intprog.project.message.model.Message;
import com.intprog.project.userProfile.model.UserProfile;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

public class MessageDto {
    private Long Id;
    private String text;
    private UserProfile profile;

    public MessageDto() {
    }

    public MessageDto(Message message) {
        Id = message.getId();
        this.text = message.getText();
        profile = message.getUserProfile();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return Id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserProfile getUser() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }
}
