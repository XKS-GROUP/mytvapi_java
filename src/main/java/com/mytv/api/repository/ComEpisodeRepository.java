package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.ComEpisode;
import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestUser.User;

public interface ComEpisodeRepository extends JpaRepository<ComEpisode, Long>{
	
	List<ComEpisode> findByUser(User u);
	List<ComEpisode> findByEpisode(Episode e);
	Optional<ComEpisode> findByUserAndEpisode(User user, Episode episode);
	
}
