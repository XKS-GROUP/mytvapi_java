package com.mytv.api.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.ressource.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

	Pays findByName(String nom);
	
	Page<Pays> findByNameContaining(String nom, Pageable p);

}
