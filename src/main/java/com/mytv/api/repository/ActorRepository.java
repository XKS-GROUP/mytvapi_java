package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.ressource.Actor;


public interface ActorRepository extends  JpaRepository<Actor, Long>{
	
   Actor findByFistNameAndLastName(String fistName, String lastName);
  
}
