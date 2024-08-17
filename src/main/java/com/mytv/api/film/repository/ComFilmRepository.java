package com.mytv.api.film.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.film.model.ComFilm;
import com.mytv.api.film.model.Film;
import com.mytv.api.user.model.User;

public interface ComFilmRepository  extends JpaRepository<ComFilm, Long>{

	List<ComFilm> findByUser(User u);
	List<ComFilm> findByFilm(Film f);
	Optional<ComFilm> findByUserAndFilm(User u, Film f);
}
