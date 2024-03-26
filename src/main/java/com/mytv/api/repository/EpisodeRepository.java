package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Episode;

@Repository
public interface EpisodeRepository extends  JpaRepository<Episode, Long> {


	List<Episode> findByNameContaining(String nom);

}
