package com.mytv.api.podcastcateg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.podcastcateg.model.CatPodcast;


@Repository
public interface CatPodcastRepository extends JpaRepository<CatPodcast, Long> {

	CatPodcast findByName(String name);
}
