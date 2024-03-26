package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Saison;

@Repository
public interface SeasonRepository extends  JpaRepository<Saison, Long>{

	Saison findByName(String name);
	List<Saison> findByNameContaining(String nom);

}
