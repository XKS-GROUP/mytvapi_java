package com.mytv.api.podcast;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionPodcastRepository extends JpaRepository<ColPodcast, Long>{

	Page<ColPodcast> findByNameOrOverviewContaining(String nom, String desc, Pageable p);
	List<ColPodcast> findByNameOrOverviewContaining(String nom, String desc);
	
	ColPodcast findByName(String name);
	Page<ColPodcast> findByName(String name, Pageable p);
	
	
}
