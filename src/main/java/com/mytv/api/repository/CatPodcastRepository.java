package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.CatPodcast;

public interface CatPodcastRepository extends JpaRepository<CatPodcast, Long> {

}
