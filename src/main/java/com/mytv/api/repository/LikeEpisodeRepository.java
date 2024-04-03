package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikeEpisode;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestUser.User;

public interface LikeEpisodeRepository extends JpaRepository<LikeEpisode, Long> {

	Optional<LikeEpisode> findByUser(User u);
	List<LikeEpisode> findByEpisode(Episode episode);
	Optional<LikeEpisode> findByUserAndEpisode(User user, Episode episode);
}
