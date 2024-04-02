package com.mytv.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikeFilm;
import com.mytv.api.model.gestMedia.Film;
import com.mytv.api.model.gestUser.User;

public interface LikeFilmRepository extends JpaRepository<LikeFilm, Long> {
	
	Optional<LikeFilm> findByUser(User u);
	List<LikeFilm> findByFilm(Film f);
	Optional<LikeFilm> findByUserAndFilm(User u, Film f);
}
