package com.mytv.api.film.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.film.model.Film;
import com.mytv.api.film.model.LikeFilm;
import com.mytv.api.user.model.User;

public interface LikeFilmRepository extends JpaRepository<LikeFilm, Long> {
	
	Optional<LikeFilm> findByUser(User u);
	List<LikeFilm> findByFilm(Film f);
	Optional<LikeFilm> findByUserAndFilm(User u, Film f);
}
