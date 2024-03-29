package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.LikeFilm;

public interface LikeFilmRepository extends JpaRepository<LikeFilm, Long> {

}
