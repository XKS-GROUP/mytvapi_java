package com.mytv.api.podcast.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.podcast.model.FavPodcast;
import com.mytv.api.podcast.model.Podcast;

@Repository
public interface FavPodcastRepository extends JpaRepository<FavPodcast, Long>{
	
	List<FavPodcast> findByUser(FirebaseUser u);
	List<FavPodcast> findByPodcast(Podcast p);
	Optional<FavPodcast> findByUserAndPodcast(FirebaseUser u, Podcast p);
}
