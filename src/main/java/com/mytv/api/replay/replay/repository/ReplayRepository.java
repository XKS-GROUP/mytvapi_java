package com.mytv.api.replay.replay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.replay.replay.model.Replay;

public interface ReplayRepository extends JpaRepository<Replay, Long>{

	Replay findByName(String name);

	List<Replay> findByNameContaining(String n);

	Page<Replay> findByNameContainingOrOverviewContaining(String val, String val2, Pageable p);

	Optional<Replay> findByNameContainingOrOverviewContaining(String val, String val2);

}
