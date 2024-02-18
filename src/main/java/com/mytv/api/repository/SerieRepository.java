package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {

}
