package com.intprog.project.music.repository;

import com.intprog.project.music.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {
}
