package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.FavFilm;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;

@Repository
public interface FavFilmRepository  extends JpaRepository<FavFilm, Long>{
	
	List<FavFilm> findByUser(User u);
	List<FavFilm> findByFilm(Film film);
	Optional<FavFilm> findByUserAndFilm(User user, Film film);
	
}
