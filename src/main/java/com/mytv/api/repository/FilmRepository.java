package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Film;


@Repository
public interface FilmRepository  extends JpaRepository<Film, Long>{
	
	Film findByName(String nom);
	List<Film> findByNameContaining(String val);

}
