package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestMedia.Radio;

@Repository
public interface RadioRepository extends JpaRepository<Radio, Long>{

	Radio findByName(String name);
	List<Radio> findByNameContaining(String nom);
	List<Radio> findByNameOrOverviewContaining(String nom, String desc, Pageable p);

}
