package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Genre;


@Repository
public interface GenreRepository extends  JpaRepository<Genre, Long> {
	
	Genre findByName(String name);
	List<Genre> findByNameContaining(String name);
	

}
