package com.mytv.api.episode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.model.LikeEpisode;
import com.mytv.api.user.model.User;

public interface LikeEpisodeRepository extends JpaRepository<LikeEpisode, Long> {

	Optional<LikeEpisode> findByUser(User u);
	List<LikeEpisode> findByEpisode(Episode episode);
	Optional<LikeEpisode> findByUserAndEpisode(User user, Episode episode);
}
