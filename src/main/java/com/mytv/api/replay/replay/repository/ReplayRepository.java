package com.mytv.api.replay.replay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.replay.replay.model.Replay;

public interface ReplayRepository extends JpaRepository<Replay, Long>{

	Replay findByName(String name);

}
