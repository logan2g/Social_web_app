package com.intprog.project.userProfile.controller;

import com.intprog.project.ProjectApplication;
import com.intprog.project.userProfile.service.UserProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/userProfile")
public class UserProfileMvcController {
    private final UserProfileService userProfileService;

    public UserProfileMvcController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public String getUserProfiles(Model model) {
        model.addAttribute("userProfile", userProfileService.findAllUserProfiles());
        return "userProfile";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editUserProfile(@PathVariable(required = false) Long id,
                            Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("userProfileDto", new UserProfileDto());
        } else {
            model.addAttribute("userProfileId", id);
            model.addAttribute("userProfileDto", new UserProfileDto(userProfileService.findUserProfile(id)));
        }
        //model.addAttribute("allUsers", departmentService.findAllDepartments());
        return "userProfile-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveUserProfile(@PathVariable(required = false) Long id,
                            @ModelAttribute @Valid UserProfileDto userProfileDto,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "userProfile-edit";
        }
        if (id == null || id <= 0) {
            userProfileService.addUserProfile(userProfileDto.getName(), userProfileDto.getSurname(), null, null,null);
        } else {
            userProfileService.updateUserProfile(id, userProfileDto.getName(), userProfileDto.getSurname(), null, null,null);
        }
        ProjectApplication.userId = id;
        return "redirect:/userProfile";
    }

    @PostMapping("/delete/{id}")
    public String deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return "redirect:/userProfile";
    }
}
