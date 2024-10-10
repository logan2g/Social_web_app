package com.intprog.project.music.controller;

import com.intprog.project.Configuration.WebConfiguration;
import com.intprog.project.music.service.MusicService;
import org.springframework.web.bind.annotation.*;
import com.intprog.project.userProfile.service.UserProfileService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/music")
public class MusicController {
    private final MusicService musicService;
    private final UserProfileService userProfileService;

    public MusicController(MusicService musicService, UserProfileService userProfileService) {
        this.musicService = musicService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public MusicDto getMusic(@PathVariable Long id) {
        return new MusicDto(musicService.findMusic(id));
    }

    @GetMapping("/")
    public List<MusicDto> getMusics() {
        return musicService.findAllMusics().stream()
                .map(MusicDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public MusicDto createMusic(@RequestBody @Valid MusicDto musicDto) {
        return new MusicDto(musicService.addMusic(musicDto.getName(), musicDto.getAuthor()));
    }

    @PutMapping("/{id}")
    public MusicDto updateMusic(@PathVariable Long id,
                                    @RequestBody @Valid MusicDto musicDto) {
        return new MusicDto(musicService.updateMusic(id, musicDto.getName(), musicDto.getAuthor()));
    }

    @DeleteMapping("/{id}")
    public MusicDto deleteMusic(@PathVariable Long id) {
        return new MusicDto(musicService.deleteMusic(id));
    }
}
