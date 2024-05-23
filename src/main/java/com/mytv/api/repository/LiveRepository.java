package com.mytv.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Live;

public interface LiveRepository  extends JpaRepository<Live, Long>{

	List<Live> findByNameContaining(String name);
	Page<Live> findByNameOrOverviewContaining(String nom, String desc, Pageable p);
	List<Live> findByNameOrOverviewContaining(String nom, String desc);
	Live findByName(String name);

}
