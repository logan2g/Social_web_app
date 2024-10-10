package com.intprog.project.message.service;

public class MessageNotFoundException extends RuntimeException{
    public MessageNotFoundException (Long id){
        super(String.format("Message with id [%s] is not found", id));
    }
}
