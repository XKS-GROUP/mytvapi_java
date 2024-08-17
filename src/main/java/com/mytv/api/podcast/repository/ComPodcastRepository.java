package com.mytv.api.podcast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.podcast.model.ComPodcast;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.user.model.User;

public interface ComPodcastRepository extends JpaRepository <ComPodcast, Long>{
		
		List<ComPodcast> findByUser(User u);
		List<ComPodcast> findByPodcast(Podcast p);
		Optional<ComPodcast> findByUserAndPodcast(User u, Podcast p);
}
