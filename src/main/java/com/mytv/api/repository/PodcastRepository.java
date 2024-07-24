package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Podcast;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {

	Podcast findByName(String nom);
	List<Podcast> findByNameContaining(String nom);
	Page<Podcast> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<Podcast> findByNameContainingOrOverviewContaining(String nom, String des);
	List<Podcast> findByTop10True();
	List<Podcast> findByTopTrue();
}
