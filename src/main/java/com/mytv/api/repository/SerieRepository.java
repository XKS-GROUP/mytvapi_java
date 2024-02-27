package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
	
	Serie findByName(String name);
	List<Serie> findByNameContaining(String nom);

}
