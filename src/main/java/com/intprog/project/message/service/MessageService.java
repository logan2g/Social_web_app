package com.intprog.project.message.service;

import com.intprog.project.userProfile.repository.UserProfileRepository;
import com.intprog.project.userProfile.service.UserProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intprog.project.message.model.*;
import com.intprog.project.message.repository.*;
import com.intprog.project.util.validation.ValidatorUtil;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserProfileService profileService;
    private final ValidatorUtil validatorUtil;

    public MessageService(MessageRepository messageRepository, UserProfileService profileService, ValidatorUtil validatorUtil) {
        this.messageRepository = messageRepository;
        this.profileService = profileService;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Message addMessage(String MessageText, Long profileId){
        final Message message = new Message(MessageText, profileService.findUserProfile(profileId));
        validatorUtil.validate(message);
        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public Message findMessage(Long id){
        final Optional<Message> mus = messageRepository.findById(id);
        return mus.orElseThrow(() -> new MessageNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    @Transactional
    public Message updateMessage(Long id, String MessageText) {
        final Message currentMessage = findMessage(id);
        currentMessage.setText(MessageText);
        validatorUtil.validate(currentMessage);
        return messageRepository.save(currentMessage);
    }

    @Transactional
    public Message deleteMessage(Long id) {
        final Message currentMessage = findMessage(id);
        messageRepository.delete(currentMessage);
        return currentMessage;
    }

    @Transactional
    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }
}
