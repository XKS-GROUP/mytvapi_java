package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikePodcast;
import com.mytv.api.model.gestMedia.Podcast;
import com.mytv.api.model.gestUser.User;

public interface LikePodcastRepository extends JpaRepository<LikePodcast, Long>{

	Optional<LikePodcast> findByUser(User u);
	List<LikePodcast> findByPodcast(Podcast p);
	Optional<LikePodcast> findByUserAndPodcast(User u, Podcast p);
}
