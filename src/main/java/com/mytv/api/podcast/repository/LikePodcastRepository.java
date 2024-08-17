package com.mytv.api.podcast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.podcast.model.LikePodcast;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.user.model.User;

public interface LikePodcastRepository extends JpaRepository<LikePodcast, Long>{

	Optional<LikePodcast> findByUser(User u);
	List<LikePodcast> findByPodcast(Podcast p);
	Optional<LikePodcast> findByUserAndPodcast(User u, Podcast p);
}
