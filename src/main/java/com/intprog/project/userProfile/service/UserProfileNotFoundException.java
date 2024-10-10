package com.intprog.project.userProfile.service;

public class UserProfileNotFoundException extends RuntimeException{
    public UserProfileNotFoundException (Long id){
        super(String.format("User profile with id [%s] is not found", id));
    }
}
