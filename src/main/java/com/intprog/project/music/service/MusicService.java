package com.intprog.project.music.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intprog.project.music.model.*;
import com.intprog.project.music.repository.*;
import com.intprog.project.util.validation.ValidatorUtil;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
    private final MusicRepository musicRepository;
    private final ValidatorUtil validatorUtil;

    public MusicService(MusicRepository musicRepository, ValidatorUtil validatorUtil) {
        this.musicRepository = musicRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Music addMusic(String MusicName, String MusicAuthor){
        final Music music = new Music(MusicName, MusicAuthor);
        validatorUtil.validate(music);
        return musicRepository.save(music);
    }

    @Transactional(readOnly = true)
    public Music findMusic(Long id){
        final Optional<Music> mus = musicRepository.findById(id);
        return mus.orElseThrow(() -> new MusicNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Music> findAllMusics() {
        return musicRepository.findAll();
    }

    @Transactional
    public Music updateMusic(Long id, String MusicName, String MusicAuthor) {
        final Music currentMusic = findMusic(id);
        currentMusic.setName(MusicName);
        currentMusic.setAuthor(MusicAuthor);
        validatorUtil.validate(currentMusic);
        return musicRepository.save(currentMusic);
    }

    @Transactional
    public Music deleteMusic(Long id) {
        final Music currentMusic = findMusic(id);
        musicRepository.delete(currentMusic);
        return currentMusic;
    }

    @Transactional
    public void deleteAllMusics() {
        musicRepository.deleteAll();
    }
}
