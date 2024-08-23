package com.mytv.api.serie.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.serie.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

	Serie findByNameIgnoreCase(String name);
	List<Serie> findByNameContaining(String nom);
	Page<Serie> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<Serie> findByNameContainingOrOverviewContaining(String n, String n2);
	List<Serie> findByTop10True();
	Serie findByTopTrue();
	
	List<Serie> findByStatusTrue();
	Page<Serie> findByStatusTrue(Pageable p);

}
