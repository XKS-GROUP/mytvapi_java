package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Saison;

public interface SeasonRepository extends  JpaRepository<Saison, Long>{

}
