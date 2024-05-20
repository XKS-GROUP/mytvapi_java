package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Saison;

@Repository
public interface EpisodeRepository extends  JpaRepository<Episode, Long> {

	List<Episode> findByIdSaison(Saison idSaison);
	List<Episode> findByNameContaining(String nom);
	List<Episode> findByNameOrOverviewContaining(String nom, String desc);
	Episode findByName(String name);

}
