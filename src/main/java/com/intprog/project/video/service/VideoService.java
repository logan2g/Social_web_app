package com.intprog.project.video.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intprog.project.video.model.*;
import com.intprog.project.video.repository.*;
import com.intprog.project.util.validation.ValidatorUtil;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final ValidatorUtil validatorUtil;

    public VideoService(VideoRepository videoRepository, ValidatorUtil validatorUtil) {
        this.videoRepository = videoRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Video addVideo(String VideoName){
        final Video video = new Video(VideoName);
        validatorUtil.validate(video);
        return videoRepository.save(video);
    }

    @Transactional(readOnly = true)
    public Video findVideo(Long id){
        final Optional<Video> mus = videoRepository.findById(id);
        return mus.orElseThrow(() -> new VideoNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    @Transactional
    public Video updateVideo(Long id, String VideoName) {
        final Video currentVideo = findVideo(id);
        currentVideo.setName(VideoName);
        validatorUtil.validate(currentVideo);
        return videoRepository.save(currentVideo);
    }

    @Transactional
    public Video deleteVideo(Long id) {
        final Video currentVideo = findVideo(id);
        videoRepository.delete(currentVideo);
        return currentVideo;
    }

    @Transactional
    public void deleteAllVideos() {
        videoRepository.deleteAll();
    }
}
