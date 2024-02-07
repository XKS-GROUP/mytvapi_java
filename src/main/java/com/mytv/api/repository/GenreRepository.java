package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Genre;

public interface GenreRepository extends  JpaRepository<Genre, Long> {

}
