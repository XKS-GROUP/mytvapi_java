package com.mytv.api.ressource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.ressource.model.CategorieLive;

public interface CategorieLiveRepository extends JpaRepository<CategorieLive, Long> {

	CategorieLive findByName(String name);

}
