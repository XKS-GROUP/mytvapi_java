package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestPub.PubliciteCat;

public interface PubliciteCatRepository extends  JpaRepository<PubliciteCat, Long>{
	
	
}
