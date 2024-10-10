package com.intprog.project.message.controller;

import com.intprog.project.Configuration.WebConfiguration;
import com.intprog.project.message.service.MessageService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable Long id) {
        return new MessageDto(messageService.findMessage(id));
    }

    @GetMapping("/")
    public List<MessageDto> getMessages() {
        return messageService.findAllMessages().stream()
                .map(MessageDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public MessageDto createMessage(@RequestBody @Valid MessageDto messageDto, Long profileId) {
        return new MessageDto(messageService.addMessage(messageDto.getText(), profileId));
    }

    @PutMapping("/{id}")
    public MessageDto updateMessage(@PathVariable Long id,
                                      @RequestBody @Valid MessageDto messageDto) {
        return new MessageDto(messageService.updateMessage(id, messageDto.getText()));
    }

    @DeleteMapping("/{id}")
    public MessageDto deleteMessage(@PathVariable Long id) {
        return new MessageDto(messageService.deleteMessage(id));
    }
}
