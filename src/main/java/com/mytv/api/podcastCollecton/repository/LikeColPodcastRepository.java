package com.mytv.api.podcastCollecton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.podcastCollecton.model.ColPodcast;
import com.mytv.api.podcastCollecton.model.LikeColPod;
import com.mytv.api.user.model.User;

@Repository
public interface LikeColPodcastRepository extends JpaRepository<LikeColPod, Long> {

	Optional<LikeColPod> findByUser(User u);

	List<LikeColPod> findByColPodcast(ColPodcast colPodcast);

	List<LikeColPod> findByColPodcast(LikeColPod p);

}
