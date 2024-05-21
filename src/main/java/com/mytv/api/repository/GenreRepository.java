package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Genre;


@Repository
public interface GenreRepository extends  PagingAndSortingRepository<Genre, Long>, JpaRepository<Genre, Long>{

	List<Genre> findByName(String name, Pageable p);
	Genre findByName(String name);
	
	@Override
	Page<Genre> findAll(Pageable pageable);
	List<Genre> findByNameContaining(String name, Pageable p);
	List<Genre> findByNameContaining(String name);

}
