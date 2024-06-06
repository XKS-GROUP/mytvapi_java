package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Saison;
import com.mytv.api.model.gestMedia.Serie;

@Repository
public interface SeasonRepository extends  JpaRepository<Saison, Long>{

	Saison findByName(String name);
	List<Saison> findByNameContaining(String nom);
	List<Saison> findByIdSerie(Serie idSerie);
	Page<Saison> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	Page<Saison> findByIdSerie(Serie Serie, Pageable p);
	Page<Saison> findBySerieRef(Long idSerie, Pageable p);
}
