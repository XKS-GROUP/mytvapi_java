package com.mytv.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.ressource.Director;


@Repository
public interface DirectorRepository extends  JpaRepository<Director, Long>{
	
	Director findByFistNameAndLastName(String fistName, String lastName);
}
