package com.mytv.api.intervenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.intervenant.model.Actor;


@Repository
public interface ActorRepository extends  JpaRepository<Actor, Long>{
	
   Actor findByFistNameAndLastName(String fistName, String lastName);
  
}
