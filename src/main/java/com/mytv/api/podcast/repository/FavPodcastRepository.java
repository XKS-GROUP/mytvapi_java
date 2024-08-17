package com.mytv.api.podcast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.podcast.model.FavPodcast;
import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.user.model.User;

@Repository
public interface FavPodcastRepository extends JpaRepository<FavPodcast, Long>{
	
	List<FavPodcast> findByUser(User u);
	List<FavPodcast> findByPodcast(Podcast p);
	Optional<FavPodcast> findByUserAndPodcast(User u, Podcast p);
}
