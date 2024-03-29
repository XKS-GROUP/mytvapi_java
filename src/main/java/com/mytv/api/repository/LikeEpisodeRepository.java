package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikeEpisode;

public interface LikeEpisodeRepository extends JpaRepository<LikeEpisode, Long> {

}
