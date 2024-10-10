package com.intprog.project.message.controller;

import com.intprog.project.ProjectApplication;
import com.intprog.project.message.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/message")
public class MessageMvcController {
    private final MessageService messageService;

    public MessageMvcController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getMessages(Model model) {
        model.addAttribute("message", messageService.findAllMessages());
        return "message";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editMessage(@PathVariable(required = false) Long id,
                               Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("messageDto", new MessageDto());
        } else {
            model.addAttribute("messageId", id);
            model.addAttribute("messageDto", new MessageDto(messageService.findMessage(id)));
        }
        //model.addAttribute("allDepartments", departmentService.findAllDepartments());
        return "message-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveMessage(@PathVariable(required = false) Long id,
                               @ModelAttribute @Valid MessageDto messageDto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "message-edit";
        }
        if (id == null || id <= 0) {
            messageService.addMessage(messageDto.getText(), ProjectApplication.userId);
        } else {
            messageService.updateMessage(id, messageDto.getText());
        }
        return "redirect:/message";
    }

    @PostMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return "redirect:/message";
    }
}
