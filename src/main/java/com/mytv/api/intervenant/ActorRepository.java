package com.mytv.api.intervenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActorRepository extends  JpaRepository<Actor, Long>{
	
   Actor findByFistNameAndLastName(String fistName, String lastName);
  
}
