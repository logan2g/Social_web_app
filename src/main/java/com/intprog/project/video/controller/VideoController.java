package com.intprog.project.video.controller;

import com.intprog.project.Configuration.WebConfiguration;
import com.intprog.project.video.service.VideoService;
import org.springframework.web.bind.annotation.*;
import com.intprog.project.userProfile.service.UserProfileService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/video")
public class VideoController {
    private final VideoService videoService;
    private final UserProfileService userProfileService;

    public VideoController(VideoService videoService, UserProfileService userProfileService) {
        this.videoService = videoService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable Long id) {
        return new VideoDto(videoService.findVideo(id));
    }

    @GetMapping("/")
    public List<VideoDto> getVideos() {
        return videoService.findAllVideos().stream()
                .map(VideoDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public VideoDto createVideo(@RequestBody @Valid VideoDto videoDto) {
        return new VideoDto(videoService.addVideo(videoDto.getName()));
    }

    @PutMapping("/{id}")
    public VideoDto updateVideo(@PathVariable Long id,
                                @RequestBody @Valid VideoDto videoDto) {
        return new VideoDto(videoService.updateVideo(id, videoDto.getName()));
    }

    @DeleteMapping("/{id}")
    public VideoDto deleteVideo(@PathVariable Long id) {
        return new VideoDto(videoService.deleteVideo(id));
    }
}
