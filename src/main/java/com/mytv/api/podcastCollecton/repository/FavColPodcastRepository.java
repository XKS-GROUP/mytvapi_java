package com.mytv.api.podcastCollecton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.podcast.model.Podcast;
import com.mytv.api.podcastCollecton.model.FavColPod;
import com.mytv.api.user.model.User;

@Repository
public interface FavColPodcastRepository extends JpaRepository<FavColPod, Long>{

	List<FavColPod> findByUser(User u);
	List<FavColPod> findByPodcast(Podcast p);
	Optional<FavColPod> findByUserAndColpodcast(User u, Podcast p);

}
