package com.intprog.project.userProfile.controller;

import com.intprog.project.Configuration.WebConfiguration;
import com.intprog.project.userProfile.controller.UserProfileDto;
import com.intprog.project.userProfile.service.UserProfileService;
import org.springframework.web.bind.annotation.*;
import com.intprog.project.userProfile.service.UserProfileService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/userProfile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public UserProfileDto getUserProfile(@PathVariable Long id) {
        return new UserProfileDto(userProfileService.findUserProfile(id));
    }

    @GetMapping("/")
    public List<UserProfileDto> getUserProfiles() {
        return userProfileService.findAllUserProfiles().stream()
                .map(UserProfileDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public UserProfileDto createUserProfile(@RequestBody @Valid UserProfileDto userProfileDto) {
        return new UserProfileDto(userProfileService.addUserProfile(userProfileDto.getName(), userProfileDto.getSurname(), null,null,null));
    }

    @PutMapping("/{id}")
    public UserProfileDto updateUserProfile(@PathVariable Long id,
                                @RequestBody @Valid UserProfileDto userProfileDto) {
        return new UserProfileDto(userProfileService.updateUserProfile(id, userProfileDto.getName(), userProfileDto.getSurname(),null,null,null));
    }

    @DeleteMapping("/{id}")
    public UserProfileDto deleteUserProfile(@PathVariable Long id) {
        return new UserProfileDto(userProfileService.deleteUserProfile(id));
    }
}
