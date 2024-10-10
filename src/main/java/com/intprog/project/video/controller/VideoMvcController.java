package com.intprog.project.video.controller;

import com.intprog.project.video.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/video")
public class VideoMvcController {
    private final VideoService videoService;

    public VideoMvcController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public String getVideos(Model model) {
        model.addAttribute("video", videoService.findAllVideos());
        return "video";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editVideo(@PathVariable(required = false) Long id,
                            Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("videoDto", new VideoDto());
        } else {
            model.addAttribute("videoId", id);
            model.addAttribute("videoDto", new VideoDto(videoService.findVideo(id)));
        }
        //model.addAttribute("allUsers", departmentService.findAllDepartments());
        return "video-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveVideo(@PathVariable(required = false) Long id,
                            @ModelAttribute @Valid VideoDto videoDto,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "video-edit";
        }
        if (id == null || id <= 0) {
            videoService.addVideo(videoDto.getName());
        } else {
            videoService.updateVideo(id, videoDto.getName());
        }
        return "redirect:/video";
    }

    @PostMapping("/delete/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return "redirect:/video";
    }
}
