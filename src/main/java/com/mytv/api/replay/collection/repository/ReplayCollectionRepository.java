package com.mytv.api.replay.collection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.replay.collection.model.ReplayCollection;

public interface ReplayCollectionRepository extends JpaRepository<ReplayCollection, Long>{
	
	ReplayCollection findByName(String name);
	List<ReplayCollection> findByNameContaining(String name);

}
