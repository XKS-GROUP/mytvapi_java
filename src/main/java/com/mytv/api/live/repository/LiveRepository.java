package com.mytv.api.live.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.live.model.Live;

public interface LiveRepository  extends JpaRepository<Live, Long>{

	List<Live> findByNameContaining(String name);
	Page<Live> findByNameContainingOrOverviewContaining(String nom, String desc, Pageable p);
	List<Live> findByNameContainingOrOverviewContaining(String nom, String desc);
	Live findByName(String name);
	List<Live> findByTop10True();
	
	Live findByTopTrue();

}
