package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.ComFilm;

public interface ComFilmRepository  extends JpaRepository<ComFilm, Long>{

}