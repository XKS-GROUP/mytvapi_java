package com.mytv.api.intervenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DirectorRepository extends  JpaRepository<Director, Long>{
	
	Director findByFistNameAndLastName(String fistName, String lastName);
}
