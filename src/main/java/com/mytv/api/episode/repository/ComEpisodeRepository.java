package com.mytv.api.episode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.episode.model.ComEpisode;
import com.mytv.api.episode.model.Episode;
import com.mytv.api.user.model.User;

public interface ComEpisodeRepository extends JpaRepository<ComEpisode, Long>{
	
	List<ComEpisode> findByUser(User u);
	List<ComEpisode> findByEpisode(Episode e);
	Optional<ComEpisode> findByUserAndEpisode(User user, Episode episode);
	
}
