package com.mytv.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytv.api.model.gestMedia.MediaType;

public interface MediaTypeRepository extends  JpaRepository<MediaType , Long> {
	
	
}
