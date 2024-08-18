package com.mytv.api.replay.categorie.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.replay.categorie.model.ReplayCateg;

@Repository
public interface ReplayCategRepository extends JpaRepository<ReplayCateg, Long> {

	ReplayCateg findByName(String name);

	List<ReplayCateg> findByName(String name, Pageable p);

	List<ReplayCateg> findByNameContaining(String name, Pageable p);

	List<ReplayCateg> findByNameContaining(String name);
}
 