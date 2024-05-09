package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.CategorieLive;

public interface CategorieLiveRepository extends JpaRepository<CategorieLive, Long> {

	CategorieLive findByName(String name);

}
