package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Podcast;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {

	Podcast findByName(String nom);
	List<Podcast> findByNameContaining(String nom);
	List<Podcast> findByNameOrOverviewContaining(String nom, String desc);
}
