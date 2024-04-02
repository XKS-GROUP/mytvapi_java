package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Film;

@Repository
public interface FavFilmRepository  extends JpaRepository<Film, Long>{

}
