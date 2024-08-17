package com.mytv.api.episode.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.episode.model.Episode;
import com.mytv.api.episode.model.FavEpisode;
import com.mytv.api.user.model.User;

@Repository
public interface FavEpisodeRepository extends JpaRepository<FavEpisode, Long>{

	List<FavEpisode> findByUser(User u);
	List<FavEpisode> findByEpisode(Episode episode);
	Optional<FavEpisode> findByUserAndEpisode(User user, Episode episode);
}
