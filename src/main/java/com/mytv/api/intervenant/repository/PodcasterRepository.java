package com.mytv.api.intervenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.intervenant.model.Podcasteur;

public interface PodcasterRepository  extends JpaRepository<Podcasteur, Long>{

}
