package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.ComPodcast;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestUser.User;

public interface ComPodcastRepository extends JpaRepository <ComPodcast, Long>{
		
		List<ComPodcast> findByUser(User u);
		List<ComPodcast> findByPodcast(Podcast p);
		Optional<ComPodcast> findByUserAndPodcast(User u, Podcast p);
}
