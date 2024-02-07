package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Episode;

public interface EpisodeRepository extends  JpaRepository<Episode, Long> {

}
