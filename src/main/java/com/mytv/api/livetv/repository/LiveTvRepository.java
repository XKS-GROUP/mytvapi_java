package com.mytv.api.livetv.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.livetv.model.LiveTv;

@Repository
public interface LiveTvRepository extends JpaRepository<LiveTv, Long>{

	LiveTv findByName(String nom);
	List<LiveTv> findByNameContaining(String val);
	Page<LiveTv> findByNameContainingOrOverviewContaining(String n, String o, Pageable p);
	List<LiveTv> findByNameContainingOrOverviewContaining(String val, String val2);
	List<LiveTv> findByTop10True();
	LiveTv findByTopTrue();

}
