package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.ColPodcast;

public interface CollectionPodcastRepository extends JpaRepository<ColPodcast, Long>{

	List<ColPodcast> findByNameOrOverviewContaining(String nom, String desc);

	ColPodcast findByName(String name);
}
