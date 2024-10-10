package com.intprog.project.userProfile.service;

import com.intprog.project.message.model.Message;
import com.intprog.project.music.model.Music;
import com.intprog.project.video.model.Video;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intprog.project.userProfile.model.*;
import com.intprog.project.userProfile.repository.*;
import com.intprog.project.util.validation.ValidatorUtil;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final ValidatorUtil validatorUtil;

    public UserProfileService(UserProfileRepository userProfileRepository, ValidatorUtil validatorUtil) {
        this.userProfileRepository = userProfileRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public UserProfile addUserProfile(String UserName, String UserSurname, Set<Message> messageSet, Set<Music> music, Set<Video> videos){
        final UserProfile userProfile = new UserProfile(UserName, UserSurname);
        if(messageSet != null) userProfile.setMessages(messageSet);
        if(music != null) userProfile.setMusicSet(music);
        if(videos != null) userProfile.setVideoSet(videos);
        validatorUtil.validate(userProfile);
        return userProfileRepository.save(userProfile);
    }

    @Transactional(readOnly = true)
    public UserProfile findUserProfile(Long id){
        final Optional<UserProfile> mus = userProfileRepository.findById(id);
        return mus.orElseThrow(() -> new UserProfileNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<UserProfile> findAllUserProfiles() {
        return userProfileRepository.findAll();
    }

    @Transactional
    public UserProfile updateUserProfile(Long id, String UserName, String UserSurname, Set<Message> messageSet, Set<Music> music, Set<Video> videos){
        final UserProfile currentUserProfile = findUserProfile(id);
        currentUserProfile.setName(UserName);
        currentUserProfile.setSurname(UserSurname);
        if(messageSet != null) currentUserProfile.setMessages(messageSet);
        if(music != null) currentUserProfile.setMusicSet(music);
        if(videos != null) currentUserProfile.setVideoSet(videos);
        validatorUtil.validate(currentUserProfile);
        return userProfileRepository.save(currentUserProfile);
    }

    @Transactional
    public UserProfile deleteUserProfile(Long id) {
        final UserProfile currentUserProfile = findUserProfile(id);
        userProfileRepository.delete(currentUserProfile);
        return currentUserProfile;
    }

    @Transactional
    public void deleteAllUserProfiles() {
        userProfileRepository.deleteAll();
    }
}
