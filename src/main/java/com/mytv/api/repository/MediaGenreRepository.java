package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.MediaGenre;

public interface MediaGenreRepository extends  JpaRepository<MediaGenre, Long> {

}
