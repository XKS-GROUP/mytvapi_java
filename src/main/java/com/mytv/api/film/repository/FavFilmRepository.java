package com.mytv.api.film.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.film.model.FavFilm;
import com.mytv.api.film.model.Film;
import com.mytv.api.user.model.User;

@Repository
public interface FavFilmRepository  extends JpaRepository<FavFilm, Long>{
	
	List<FavFilm> findByUser(User u);
	List<FavFilm> findByFilm(Film film);
	Optional<FavFilm> findByUserAndFilm(User user, Film film);
	
}
