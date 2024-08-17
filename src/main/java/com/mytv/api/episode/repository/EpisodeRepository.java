package com.mytv.api.episode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.episode.model.Episode;
import com.mytv.api.saison.model.Saison;

@Repository
public interface EpisodeRepository extends  JpaRepository<Episode, Long> {

	List<Episode> findByIdSaison(Saison idSaison);
	List<Episode> findByIdSaison(Saison idSaison, Pageable p);
	//List<Episode> findBySaisonRef(Long saison, Pageable p);
	List<Episode> findByNameContaining(String nom);
	Page<Episode> findBySaisonRef(Long ref, Pageable p);
	Page<Episode> findByIdSerie(Long ref, Pageable p);
	Page<Episode> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<Episode> findByNameContainingOrOverviewContaining(String nom, String desc);
	Episode findByName(String name);
	List<Episode> findByNumero(int numero);

}
