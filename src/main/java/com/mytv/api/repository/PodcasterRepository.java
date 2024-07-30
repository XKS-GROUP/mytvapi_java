package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Podcasteur;

public interface PodcasterRepository  extends JpaRepository<Podcasteur, Long>{

}
