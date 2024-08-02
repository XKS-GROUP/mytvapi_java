package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.ressource.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

	Pays findByName(String nom);
	
	List<Pays> findByNameContaining(String nom);

}
