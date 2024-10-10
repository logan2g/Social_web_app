package com.intprog.project.video.service;

public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException (Long id){
        super(String.format("Video with id [%s] is not found", id));
    }
}
