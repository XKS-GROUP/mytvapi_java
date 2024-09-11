package com.mytv.api.intervenant.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.intervenant.model.Actor;


@Repository
public interface ActorRepository extends  JpaRepository<Actor, Long>{
	
   Actor findByFistNameAndLastName(String fistName, String lastName);
   
   List<Actor> findByFistNameContainingAndLastNameContaining(String fistName, String lastName);

   List<Actor> findByFistNameContainingAndLastNameContaining(String val, String val2, Pageable p);
  
}
