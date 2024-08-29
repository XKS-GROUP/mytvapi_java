package com.mytv.api.film.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.film.model.Film;


@Repository
public interface FilmRepository  extends JpaRepository<Film, Long>{

	Film findByName(String nom);
	
	List<Film> findByNameContaining(String val);
	List<Film> findByNameContaining(String val, Pageable p);
	Page<Film> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<Film> findByNameContainingOrOverviewContaining(String nom, String desc);
	List<Film> findByTop10True();

	Film findByTopTrue();

}
