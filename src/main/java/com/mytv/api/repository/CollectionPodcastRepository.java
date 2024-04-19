package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.ColPodcast;

public interface CollectionPodcastRepository extends JpaRepository<ColPodcast, Long>{

	
}
