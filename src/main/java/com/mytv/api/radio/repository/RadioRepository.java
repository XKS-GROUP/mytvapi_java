package com.mytv.api.radio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.radio.model.Radio;

@Repository
public interface RadioRepository extends JpaRepository<Radio, Long>{

	Radio findByName(String name);
	List<Radio> findByNameContaining(String nom);
	Page<Radio> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<Radio> findByNameContainingOrOverviewContaining(String n, String n2);
	List<Radio> findByTop10True();
	Radio findByTopTrue();

}
