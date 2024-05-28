package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Episode;
import com.mytv.api.model.gestMedia.Saison;

@Repository
public interface EpisodeRepository extends  JpaRepository<Episode, Long> {

	List<Episode> findByIdSaison(Saison idSaison);
	List<Episode> findByIdSaison(Saison idSaison, Pageable p);
	//List<Episode> findBySaisonRef(Long saison, Pageable p);
	List<Episode> findByNameContaining(String nom);
	Page<Episode> findBySaisonRef(Long ref, Pageable p);
	Page<Episode> findByNameOrOverviewContaining(String nom, String desc, Pageable p);
	Episode findByName(String name);

}
