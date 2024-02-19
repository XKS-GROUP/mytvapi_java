package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytv.api.model.gestPub.PubliciteCat;


@Repository
public interface PubliciteCatRepository extends  JpaRepository<PubliciteCat, Long>{
	
	
}
