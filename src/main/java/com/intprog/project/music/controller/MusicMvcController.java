package com.intprog.project.music.controller;

import com.intprog.project.music.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/music")
public class MusicMvcController {
    private final MusicService musicService;

    public MusicMvcController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping
    public String getMusics(Model model) {
        model.addAttribute("music", musicService.findAllMusics());
        return "music";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editMusic(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("musicDto", new MusicDto());
        } else {
            model.addAttribute("musicId", id);
            model.addAttribute("musicDto", new MusicDto(musicService.findMusic(id)));
        }
        //model.addAttribute("allUsers", departmentService.findAllDepartments());
        return "music-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveMusic(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid MusicDto musicDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "music-edit";
        }
        if (id == null || id <= 0) {
            musicService.addMusic(musicDto.getName(), musicDto.getAuthor());
        } else {
            musicService.updateMusic(id, musicDto.getName(), musicDto.getAuthor());
        }
        return "redirect:/music";
    }

    @PostMapping("/delete/{id}")
    public String deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return "redirect:/music";
    }
}
