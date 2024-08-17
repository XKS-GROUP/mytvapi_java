package com.mytv.api.serie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.film.model.FilmGenre;

@Repository
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {

}
