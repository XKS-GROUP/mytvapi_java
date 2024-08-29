package com.mytv.api.podcastCollecton.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.podcastCollecton.model.ColPodcast;

@Repository
public interface CollectionPodcastRepository extends JpaRepository<ColPodcast, Long>{

	Page<ColPodcast> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<ColPodcast> findByNameContainingOrOverviewContaining(String nom, String desc);
	
	ColPodcast findByName(String name);
	Page<ColPodcast> findByName(String name, Pageable p);
	List<ColPodcast> findByTop10True();
	ColPodcast findByTopTrue();
	
	
	
}
