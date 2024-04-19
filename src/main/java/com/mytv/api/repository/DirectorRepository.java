package com.mytv.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.Director;

public interface DirectorRepository extends  JpaRepository<Director, Long>{
	
	Director findByFistNameAndLastName(String fistName, String lastName);
}
